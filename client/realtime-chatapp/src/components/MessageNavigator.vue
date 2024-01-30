<script setup>
import SearchChat from './SearchChat.vue';
import MessageNavigatorItem from './MessageNavigatorItem.vue';

const props = defineProps(['data', 'activeUser']);
var activeUser = props.activeUser;
</script>

<template>
    <div class="message-nav">
        <SearchChat />

        <template v-for="(value, key) in data" :key="key">
            <div @click="$emit('activeUserChanged', key)">
                <MessageNavigatorItem
                    :activated="key == activeUser"
                    :userData="value.userData"
                    :messages="value.messages" />
            </div>
        </template>
    </div>
</template>

<style scoped>
.message-nav {
    /* border: 1px solid red; */
}
</style>
