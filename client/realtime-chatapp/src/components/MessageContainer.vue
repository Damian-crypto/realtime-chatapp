<script setup>
import MessageCard from './MessageCard.vue';
import MessageContainerHeader from './MessageContainerHeader.vue';
import MessageContainerFooter from './MessageContainerFooter.vue';

const props = defineProps(['data', 'activeUser']);
const users = props.data.users;
const activeUser = props.activeUser;
// console.log(activeUser);

var noData = Object.keys(props.data.messages) == 0;
var messages = {};
var isGroup = false;
if (!noData) {
    messages = props.data.messages[activeUser].messages;

    // console.log(messages);

    if (Object.keys(users[activeUser]).includes('groupMembers')) {
        isGroup = true;
    }
}

var selectedMessage = -1;
var mouseX = 0, mouseY = 0;

function handleMouseMove(evt) {
    // console.log(mouseX, mouseY);
    mouseX = evt.clientX;
    mouseY = evt.clientY;
}

function handleMouseClick(evt) {
    // console.log(mouseX, mouseY);
    hideContextMenu();
}

function hideContextMenu() {
    var contextMenu = document.getElementById("context-menu-container");
    contextMenu.style.display = "none";
}

function handleContextMenu(idx) {
    // console.log(mouseX, mouseY);
    selectedMessage = idx;
    
    var contextMenu = document.getElementById("context-menu-container");
    contextMenu.style.display = "inline-flex";
    contextMenu.style.left = `${mouseX}px`;
    contextMenu.style.top = `${mouseY}px`;
}

function showInfoSelected(evt) {
    // console.log(mouseX, mouseY);
    // console.log(messages);
    alert(JSON.stringify(messages[selectedMessage]));
    hideContextMenu();
}

function deleteSelected(evt) {
    hideContextMenu();
}
</script>

<template>
    <div class="message-container" v-if="!noData" @click="handleMouseClick" @mousemove="handleMouseMove">
        <div class="container-header">
            <MessageContainerHeader :user-data="users[activeUser]" :users="users" />
        </div>
        <template v-for="(msg, index) in messages" :key="index">
            <div @contextmenu.prevent="handleContextMenu(index)">
                <MessageCard :group-chat="isGroup" :message="msg" :users="users" />
            </div>
        </template>

        <MessageContainerFooter />
    </div>
    <div class="message-container" v-else @click="handleMouseClick" @mousemove="handleMouseMove">
        No Data
    </div>

    <div id="context-menu-container">
        <div id="menu-show-info" class="menu-item" @click="showInfoSelected">Show Information</div>
        <div id="menu-delete" class="menu-item" @click="deleteSelected">Delete</div>
    </div>
</template>

<style scoped>
#context-menu-container {
    display: none;
    background-color: #414141;
    color: white;
    z-index: 999;
    border: 1px solid #b9b9b9;
    padding: 3px;
    border-radius: 8px;
    position: fixed;
    flex-direction: column;
}

.menu-item {
    /*border: 1px solid red;*/

    display: inline-block;
    padding: 10px;
    width: 150px;
    cursor: default;
    border-radius: 8px;
}

.menu-item:hover {
    background-color: #d9d9d9;
    color: black;
    border-radius: 8px;
}

.message-container {
    /* border: 1px solid red; */
    flex-grow: 1;
}
</style>
