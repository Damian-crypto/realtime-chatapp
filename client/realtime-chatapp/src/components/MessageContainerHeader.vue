<script setup>
import { Data } from './store';
import ColorList from './ColorList.vue';

const props = defineProps(['active-user']);
const users = Data.data.users;
const userData = Data.data.users[props.activeUser];

var isGroup = false;
var userName = userData['userName'];
var groupMembers = [];
if (Object.keys(userData).includes('groupMembers')) {
    isGroup = true;
    userData.groupMembers.forEach(memberID => groupMembers.push(users[memberID]['userName']));
}

const randomColors = ColorList.colors;
const color = randomColors[userName[0].charCodeAt(0) - 'A'.charCodeAt(0)];
</script>

<template>
    <div class="container">
        <div class="user-image" :style="{ 'background-color': color }">{{ userName[0] }}</div>
        <div class="username-section">
            <span class="username-label">{{ userName }}</span>
            <span v-if="isGroup" class="timestamp">{{ groupMembers.join(', ') }}</span>
            <span v-else class="timestamp">Last online @ {{ new Date(userData['lastOnline']).toLocaleString() }}</span>
        </div>
    </div>
</template>

<style scoped>
.container {
    /* border: 1px solid red; */

    background-color: #3b3b3b;
    color: white;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 15px;
}

.user-image {
    display: flex;
    /* background-color: blueviolet; */
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

.timestamp {
    font-size: 12px;
}
</style>
