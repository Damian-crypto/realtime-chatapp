<script setup>
</script>

<template>
    <div class="input-msg-bar">
        <input id="txt-msg" type="text" placeholder="Type your message here...">
        <div class="btn-send"><img class="send-icon" src="/icons/send_icon.png"></div>
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
}

.btn-send:hover {
    /* border: 1px solid red; */

    display: flex;
    background-color: #414141;
    padding-right: 1px;
    padding-left: 4px;
    border-radius: 999px;
    margin-left: 10px;
}
</style>
