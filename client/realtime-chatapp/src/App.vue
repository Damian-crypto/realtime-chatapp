<script setup>
import MessageContainer from './components/MessageContainer.vue';
import MessageNavigator from './components/MessageNavigator.vue';

var messages = {
    1002304: [
        {
            id: 0,
            sender: 0,
            content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
    ornare nibh pellentesque est. Imperdiet gravida et 
    ullamcorper justo risus. Placerat vitae a nulla imperdiet 
    praesent ac senectus convallis`,
            timestamp: '12:12 PM 27/01/2024'
        },
        {
            id: 1,
            sender: 1002304,
            content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
    ornare nibh pellentesque est. Imperdiet gravida et 
    ullamcorper justo risus. Placerat vitae a nulla imperdiet 
    praesent ac senectus convallis`,
            timestamp: '1:00 PM 27/01/2024'
        }
    ]
};
</script>

<template>
    <div class="app-window">
        <MessageNavigator :messages="messages"/>
        <MessageContainer :messages="messages"/>
    </div>
</template>

<style scoped>
.app-window {
    display: flex;
    flex-direction: row;
}
</style>
