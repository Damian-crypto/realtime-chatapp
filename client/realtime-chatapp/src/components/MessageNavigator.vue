<script setup>
import SearchChat from './SearchChat.vue';
import MessageNavigatorItem from './MessageNavigatorItem.vue';

const props = defineProps(['data', 'activeUser']);
const users = props.data.users;
const messages = props.data.messages;
const activeUser = props.activeUser;

// console.log(props.data);
</script>

<template>
    <div class="message-nav">
        <SearchChat />

        <template v-for="(value, index) in messages" :key="index">
            <div @click="$emit('activeUserChanged', value.userData.userID)">
                <MessageNavigatorItem
                    :activated="value.userData.userID == activeUser"
                    :users="users"
                    :user-data="value.userData"
                    :messages="value.messages" />
            </div>
        </template>
    </div>
</template>

<style scoped>
.message-nav {
    /* border: 1px solid red; */

    border-right: 1px solid #414141;
}
</style>
