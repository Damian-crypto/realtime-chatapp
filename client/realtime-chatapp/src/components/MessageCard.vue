<script setup>
import { Data } from './store';
import ColorList from './ColorList.vue';

const randomColors = ColorList.colors;

const props = defineProps(['messageIndex', 'groupChat', 'activeUser']);
const messageIndex = props.messageIndex;
const activeUser = props.activeUser;
const isGroup = props.groupChat;
const users = Data.data.users;
// console.log(Data.data.messages);
// console.log(props.messageIndex);
const message = Data.data.messages[activeUser].messages[messageIndex];
const userName = users[message.sender].userName;

function getColor(userName) {
    return randomColors[userName[0].charCodeAt(0) - 'A'.charCodeAt(0)];
}

</script>

<template>
    <div class="chat-message">
        <div class="message-card" :style="[
            message.sender == 1
                ?
                { 'background-color': '#313131' }
                :
                { 'background-color': '#414141' }]">

            <div v-if="isGroup" class="msg-owner" :class="message.sender != 0 ? 'right-sided' : 'left-sided'">
                <div class="user-image" :style="{ 'background-color': getColor(userName) }">{{ userName[0] }}</div>
                <div class="username-section">
                    <span class="username-label">{{ userName }}</span>
                </div>
            </div>
            <div class="message-content" :style="[
                message.sender == 1
                    ?
                    { 'text-align': 'left' }
                    :
                    { 'text-align': 'right' }]">
                {{ message.content }}
            </div>
            <div class="message-footer" :style="[
                message.sender == 1
                    ?
                    { 'flex-direction': 'row-reverse' }
                    :
                    { 'flex-direction': 'row' }]">
                <div class="timestamp">
                    {{ new Date(message.timestamp).toLocaleString() }}
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.msg-owner {
    /* border: 1px solid red; */

    display: flex;
}

.right-sided {
    /* border: 1px solid yellow; */
    
    /* display: flex; */
    float: right;
}

.left-sided {
    float: left;
}

.user-image {
    /* border: 1px solid red; */

    display: flex;
    border-radius: 999px;
    width: 30px;
    height: 30px;
    font-size: 24px;
    align-items: center;
    justify-content: center;
    color: white;
}

.username-section {
    /* border: 1px solid red; */

    display: flex;
    flex-direction: column;
    margin-left: 5px;
    align-items: center;
    justify-content: center;
}

.username-label {
    /* border: 1px solid red; */

    font-size: 16px;
    font-weight: bold;
}

.message-card {
    /* border: 1px solid red; */

    padding: 10px;
    cursor: default;
}

.message-content {
    padding: 10px;
    color: white;
    font-size: 16px;
    display: inline-block;
}

.message-footer {
    display: flex;
}

.timestamp {
    font-size: 0.8em;
    color: #888;
}
</style>
