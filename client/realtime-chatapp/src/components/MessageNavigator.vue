<script setup>
import { Data } from './store';
import SearchChat from './SearchChat.vue';
import MessageNavigatorItem from './MessageNavigatorItem.vue';

const props = defineProps(['activeUser', 'metaData']);
const metaData = props.metaData;
const messages = Data.data.messages;
const activeUser = props.activeUser;
</script>

<template>
    <div class="message-nav">
        <SearchChat />

        <div @click="$emit('activeUserChanged', metaData.myUserId)">
            <MessageNavigatorItem
                :me="true"
                :active-user="metaData.myUserId"
                :activated="activeUser == metaData.myUserId"
                />
        </div>
        <div v-for="(value, index) in messages" :key="index">
            <div @click="$emit('activeUserChanged', index)">
                <MessageNavigatorItem
                    :active-user="activeUser"
                    :activated="activeUser == index"
                    :messages-index="index" />
            </div>
        </div>
    </div>
</template>

<style scoped>
.message-nav {
    /* border: 1px solid red; */

    border-right: 1px solid #414141;
}
</style>
