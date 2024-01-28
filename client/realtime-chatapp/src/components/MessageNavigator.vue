<script setup>
import SearchChat from './SearchChat.vue';
import MessageNavigatorItem from './MessageNavigatorItem.vue';

const props = defineProps(['messages']);
const messages = props.messages;
</script>

<template>
    <div class="message-nav">
        <SearchChat />

        <template v-for="(key, value) in messages">
            <MessageNavigatorItem :messages="messages[value]" />
        </template>
    </div>
</template>

<style scoped>
.message-nav {
    border: 1px solid red;
}
</style>
