<script setup>
import { Data } from './store';
import ColorList from './ColorList.vue';

const props = defineProps(['messagesIndex', 'activeUser', 'me', 'activated']);

var index, messageCount, lastMessage, content;
const users = Data.data.users;
const activated = props.activated;

if (!props.me) {
    index = props.messagesIndex;
    messageCount = Data.data.messages[index].messages.length;

    if (messageCount > 0) {
        lastMessage = Data.data.messages[index].messages[messageCount - 1];
        content = lastMessage.content;
    } else {
        content = new Date(users[index]['lastOnline']).toLocaleString();
    }
} else {
    index = props.activeUser;
    content = "Profile and Settings";
}

const userName = users[index]['userName'];

const randomColors = ColorList.colors;
const color = randomColors[userName[0].charCodeAt(0) - 'A'.charCodeAt(0)];
</script>

<template>
    <div class="container" :class="activated ? 'active' : 'deactive'">
        <div class="user-image" :style="{ 'background-color': color }">{{ userName[0] }}</div>
        <div class="username-section">
            <span class="username-label">{{ userName }}</span>
            <span class="recent-message">
                <template v-if="lastMessage != null">
                    <template v-if="lastMessage.sender == 1">You:</template>
                    <template v-else>{{ userName }}:&nbsp;</template>
                    {{ content.substr(0, Math.min(20, content.length)) }}...
                </template>
                <template v-else>
                    {{ content }}
                </template>
            </span>
        </div>
    </div>
</template>

<style scoped>
.active {
    background-color: #00477e;
}

.deactive {
    background-color: #1f1f1f;
}

.container {
    /* border: 1px solid red; */

    color: white;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px;
    cursor: default;
}

.container:hover {
    /* border: 1px solid red; */

    background-color: #005fa8;
    color: white;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px;
    cursor: default;
}

.user-image {
    display: flex;
    background-color: blueviolet;
    border-radius: 999px;
    width: 80px;
    height: 80px;
    font-size: 48px;
    align-items: center;
    justify-content: center;
    color: white;
}

.username-section {
    display: flex;
    flex-direction: column;
    margin-left: 20px;
}

.username-label {
    font-size: 16px;
    font-weight: bold;
}

.recent-message {
    font-size: 12px;
}
</style>
