<script setup>
import { Data } from './store';
import { deleteMessage } from '../utils/utils';
import MessageCard from './MessageCard.vue';
import MessageContainerHeader from './MessageContainerHeader.vue';
import MessageContainerFooter from './MessageContainerFooter.vue';

const props = defineProps(['activeUser', 'metaData']);
const users = Data.data.users;
const activeUser = props.activeUser;
const metaData = props.metaData;

var messages = {};
var isGroup = false;
var noData = Object.keys(Data.data.messages) == 0;
if (!noData) {
    if (Object.keys(users[activeUser]).includes('groupMembers')) {
        isGroup = true;
    }
}

// console.log(activeUser);
// console.log(Data.data.messages);
// console.log(Data.data.messages[activeUser]);

var profile = false;
if (activeUser == metaData.myUserId) {
    profile = true;
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
    alert(JSON.stringify(Data.data.messages[activeUser].messages[selectedMessage]));
    hideContextMenu();
}

function deleteSelected(evt) {
    // console.log(Data.data.messages[activeUser].messages[selectedMessage].id);
    deleteMessage(metaData, Data.data.messages[activeUser].messages[selectedMessage].id, () => {}, () => {});
    hideContextMenu();
}

function goBottom() {
    window.location.hash = "";
    window.location.hash = "txt-msg";
}
</script>

<template>

        <div class="message-container" v-if="!noData" @click="handleMouseClick" @mousemove="handleMouseMove">
            <div class="container-header">
                <MessageContainerHeader :active-user="activeUser" />
            </div>
            <div v-if="profile">
                <h1>This is your profile</h1>
                <ul>
                    <li>Name</li>
                    <li>Password</li>
                    <li>Email</li>
                    <li>Phone Number</li>
                    <li>Profile Picture</li>
                    <li>Logout</li>
                </ul>
            </div>
            <div v-else>
                <template v-for="(msg, index) in Data.data.messages[activeUser].messages" :key="index">
                    <div @contextmenu.prevent="handleContextMenu(index)">
                        <MessageCard :group-chat="isGroup" :active-user="activeUser" :message-index="index" />
                    </div>
                </template>
                <MessageContainerFooter @go-bottom="goBottom" :meta-data="metaData" />
            </div>
        </div>
        <div class="message-container" v-else @click="handleMouseClick" @mousemove="handleMouseMove">
            No Data
        </div>

        <a id="scroll-down" hidden></a>

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
