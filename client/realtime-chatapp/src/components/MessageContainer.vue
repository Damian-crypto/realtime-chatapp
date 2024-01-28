<script setup>
import MessageCard from './MessageCard.vue';
import MessageContainerHeader from './MessageContainerHeader.vue';

const props = defineProps(['messages']);
</script>

<template>
    <div class="message-container">
        <MessageContainerHeader />
        <template v-for="(msg, index) in messages[1002304]" :key="index">
            <MessageCard :message="msg" />
        </template>
    </div>
</template>

<style scoped>
.message-container {
    border: 1px solid red;
}
</style>
