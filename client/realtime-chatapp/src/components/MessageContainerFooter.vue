<script setup>
import { Data } from './store';
import { getData } from '@/utils/utils';
import { ref } from 'vue';

const props = defineProps(['metaData']);
const emit = defineEmits(['goBottom']);
const metaData = props.metaData;

const txtMsg = ref("");

function sendMessage() {
    // var txtMsg = document.getElementById("txt-msg");

    const body = {
        content: txtMsg.value,
        receiver: metaData.activeUser
    };

    fetch(`${metaData.baseURL}/new-message/${metaData.myUserId}`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
    .then((res) => {
        if (!res.ok) {
            throw new Error(`Message not sent (🧐) => ${res.status}!`);
        }

        return res.json();
    })
    .then((data) => {
        getData(metaData, (res) => {
            Data.data = res
            txtMsg.value = "";
            emit('goBottom');
        });
    })
    .catch((err) => console.error(`Error(🫤) sending message => ${err}`));
}
</script>

<template>
    <div class="input-msg-bar">
        <input id="txt-msg" type="text" placeholder="Type your message here..." @change="sendMessage" v-model="txtMsg">
        <div class="btn-send" @click="sendMessage"><img class="send-icon" src="/icons/send_icon.png"></div>
    </div>
</template>

<style scoped>
#txt-msg {
    border: none;
    border-radius: 50px;
    font-size: 20px;
    padding: 0 20px 0 20px;
    background-color: #414141;
    color: white;
    height: 50px;
    flex-grow: 1;
}

#txt-msg::placeholder {
    color: white;
    opacity: 0.5;
}

.input-msg-bar {
    /* border: 1px solid red; */

    padding: 20px;
    display: flex;
    flex-direction: row;
}

.send-icon {
    /* border: 1px solid red; */

    height: 50px;
}

.btn-send {
    /* border: 1px solid red; */

    display: flex;
    background-color: #414141;
    padding-right: 5px;
    border-radius: 999px;
    margin-left: 10px;
    cursor: pointer;
}

.btn-send:hover {
    /* border: 1px solid red; */

    display: flex;
    background-color: #005fa8;
    padding-right: 5px;
    border-radius: 999px;
    margin-left: 10px;
    cursor: pointer;
}
</style>
