<script setup>
</script>

<template>
    <div class="input-msg-bar">
        <input id="txt-msg" type="text" placeholder="Type your message here...">
        <a class="btn-send" href=""><img class="send-icon" src="/icons/send_icon.png"></a>
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
    border: 1px solid red;

    padding: 20px;
    display: flex;
    flex-direction: row;
}

.send-icon {
    border: 1px solid red;

    display: flex;
    border-radius: 999px;
    background-color: #414141;
    text-align: center;
    align-items: center;
    height: 50px;
}

.btn-send {
    border: 1px solid red;

    padding-left: 10px;
}
</style>
