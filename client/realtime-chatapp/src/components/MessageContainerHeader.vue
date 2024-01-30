<script setup>
const props = defineProps(['user-data']);
const userData = props.userData;
const userName = userData['userName'];

const randomColors = [ '#1F8B4C', '#FAA42A', '#2354D7', '#9CBB44', '#E87A90', '#7541B9', '#B3E84E', '#3457F9', '#B0C14F', '#D27D8E', '#0A156D', '#E75F09', '#3C5C8F', '#F6D252', '#5626B7', '#88A53E', '#E94E63', '#415ABF', '#B0DA6B', '#8E1A30', '#168E5E', '#F296A1', '#6B3277', '#B7C763', '#EF762D' ];
const color = randomColors[userName[0].charCodeAt(0) - 'A'.charCodeAt(0)];

</script>

<template>
    <div class="container">
        <div class="user-image" :style="{ 'background-color': color }">{{ userName[0] }}</div>
        <div class="username-section">
            <span class="username-label">{{ userName }}</span>
            <span class="timestamp">Last online @ {{ userData['lastOnline'][1] }}</span>
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
