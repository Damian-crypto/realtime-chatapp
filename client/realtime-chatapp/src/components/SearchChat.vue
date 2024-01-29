<script setup>
</script>

<template>
    <div class="container">
        <input id="txt-search" type="text" placeholder="Search Chat">
        <a clsas="btn-search" href=""><img class="search-icon" src="/icons/search_icon.png"></a>
    </div>
</template>

<style scoped>
.container {
    padding: 39px;
    display: flex;
}

.btn-search {
    border: 1px solid red;
}

.search-icon {
    width: 28px;
    top: 47px;
    left: 270px;
    padding: 1px;
    position: absolute;
}

#txt-search {
    font-size: 18px;
    border: none;
    height: 40px;
    border-radius: 50px;
    padding: 0 40px 0 15px;
    width: 250px;
    background-color: #414141;
    color: white;
}
</style>
