<script setup>
import { Data } from './store';
import SearchChat from './SearchChat.vue';
import MessageNavigatorItem from './MessageNavigatorItem.vue';

const props = defineProps(['activeUser']);
const users = Data.data.users;
const messages = Data.data.messages;
const activeUser = props.activeUser;

var noData = Object.keys(Data.data.users).length == 0;

// console.log(props.data);
</script>

<template>
    <div class="message-nav">
        <SearchChat />

        <div v-if="!noData">
            <div v-for="(value, index) in messages" :key="index">
                <div @click="$emit('activeUserChanged', index)">
                    <MessageNavigatorItem
                        :activated="index == activeUser"
                        :userId="index"
                        :users="users"
                        :messages="value.messages" />
                </div>
            </div>
        </div>
        <div v-else>
            No Data
        </div>
    </div>
</template>

<style scoped>
.message-nav {
    /* border: 1px solid red; */

    border-right: 1px solid #414141;
}
</style>
