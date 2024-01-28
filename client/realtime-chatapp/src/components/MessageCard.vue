<script setup>
const props = defineProps(['message']);
</script>

<template>
    <div class="chat-message">
        <div
            class="message-card"
            :style="[
                message.sender == 0
                ?
                { 'background-color': '#f0faff' }
                :
                { 'background-color': '#fffde9' }]">
            <div
                class="message-content"
                :style="[
                    message.sender == 0
                    ?
                    { 'text-align': 'left' }
                    :
                    { 'text-align': 'right' }]">
                {{ message.content }}
            </div>
            <div
                class="message-footer"
                :style="[
                    message.sender == 0
                    ?
                    { 'flex-direction': 'row-reverse' }
                    :
                    { 'flex-direction': 'row' }]">
                <div class="timestamp">
                    {{ message.timestamp }}
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.message-card {
    border: 1px solid red;
    padding: 10px;
}

.message-content {
    padding: 10px;
    color: black;
    font-size: 16px;
}

.message-footer {
    display: flex;
}

.timestamp {
    font-size: 0.8em;
    color: #888;
}
</style>
