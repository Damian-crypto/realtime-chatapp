<script setup>
import ColorList from './ColorList.vue';

const props = defineProps(['messages', 'activated', 'users', 'userId']);

// console.log('users', props.users);
// console.log('messages', props.messages.length);
// console.log('messages', props.messages);
// console.log(props.activated);

const users = props.users;
const userID = props.userId;
const userName = users[userID]['userName'];
const messageCount = props.messages.length;
const activated = props.activated;

const randomColors = ColorList.colors;
const color = randomColors[userName[0].charCodeAt(0) - 'A'.charCodeAt(0)];

var lastMessage = null;
var content = null;

if (messageCount > 0) {
    lastMessage = props.messages[messageCount - 1];
    content = lastMessage.content;
} else {
    content = new Date(users[userID]['lastOnline']).toLocaleString();
}
</script>

<template>
    <div class="container" :class="activated ? 'active' : 'deactive'">
        <div class="user-image" :style="{ 'background-color': color }">{{ userName[0] }}</div>
        <div class="username-section">
            <span class="username-label">{{ userName }}</span>
            <span class="recent-message">
                <template v-if="lastMessage != null">
                    <template v-if="lastMessage.sender == 0">You:</template>
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
