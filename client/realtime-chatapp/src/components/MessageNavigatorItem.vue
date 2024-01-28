<script setup>
const props = defineProps(['messages']);
const messages = props.messages;
const n = messages.length;
const message = messages[n - 1];
const content = n > 0 ? message.content : "";

console.log(message);
console.log(content);
</script>

<template>
    <div class="container">
        <div class="user-image">J</div>
        <div class="username-section">
            <span class="username-label">John Doe</span>
            <span class="recent-message">
                <template v-if="message.sender == 0">You:</template>
                <template v-else>John Doe:</template>
                {{ content.substr(0, Math.min(20, content.length)) }}...
            </span>
        </div>
    </div>
</template>

<style scoped>
.container {
    border: 1px solid red;

    background-color: #f0faff;
    color: black;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px;
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
