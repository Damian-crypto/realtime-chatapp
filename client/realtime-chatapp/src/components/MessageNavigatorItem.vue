<script setup>
const props = defineProps(['messages', 'userData', 'activated']);
const userData = props.userData;
const userName = userData['userName'];
const messageCount = props.messages.length;
const activated = props.activated;

const randomColors = [ '#1F8B4C', '#FAA42A', '#2354D7', '#9CBB44', '#E87A90', '#7541B9', '#B3E84E', '#3457F9', '#B0C14F', '#D27D8E', '#0A156D', '#E75F09', '#3C5C8F', '#F6D252', '#5626B7', '#88A53E', '#E94E63', '#415ABF', '#B0DA6B', '#8E1A30', '#168E5E', '#F296A1', '#6B3277', '#B7C763', '#EF762D' ];
const color = randomColors[userName[0].charCodeAt(0) - 'A'.charCodeAt(0)];

var lastMessage = null;
var content = null;

if (messageCount > 0) {
    lastMessage = props.messages[messageCount - 1];
    content = lastMessage.content;
} else {
    content = userData['lastOnline'].join(' ');
}

// console.log(userData);
// console.log(lastMessage);
// console.log(content);
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
