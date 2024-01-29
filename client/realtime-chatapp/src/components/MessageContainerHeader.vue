<script setup>
</script>

<template>
    <div class="container">
        <div class="user-image">J</div>
        <div class="username-section">
            <span class="username-label">John Doe</span>
            <span class="timestamp">Last online @ 1:00 PM</span>
        </div>
    </div>
</template>

<style scoped>
.container {
    border: 1px solid red;

    background-color: #313131;
    color: white;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 15px;
}

.user-image {
    display: flex;
    background-color: blueviolet;
    border-radius: 999px;
    width: 80px;
    height: 80px;
    font-size: 48px;
    align-items: center;
    justify-content: center;
    color: white;
}

.username-section {
    display: flex;
    flex-direction: column;
    margin-left: 20px;
}

.username-label {
    font-size: 16px;
    font-weight: bold;
}

.timestamp {
    font-size: 12px;
}
</style>
