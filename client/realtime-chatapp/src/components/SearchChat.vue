<script setup>
</script>

<template>
    <div class="container">
        <input id="txt-search" type="text" placeholder="Search Chat">
        <a class="btn-search" href="">
            <img class="search-icon" src="/icons/search_icon.png">
        </a>
    </div>
</template>

<style scoped>
.container {
    padding: 39px;
    display: flex;
}

.btn-search {
    /* border: 1px solid red; */
    
    height: 40px;
    padding-right: 15px;
    border-top-right-radius: 50px;
    border-bottom-right-radius: 50px;
    background-color: #414141;
}

.search-icon {
    width: 28px;
    /* top: 47px; */
    /* left: 270px; */
    padding-left: 5px;
    padding-top: 10px;
}

#txt-search {
    font-size: 18px;
    border: none;
    /* border-radius: 50px; */
    border-top-left-radius: 50px;
    border-bottom-left-radius: 50px;
    height: 40px;
    padding-left: 20px;
    width: 220px;
    background-color: #414141;
    color: white;
}
</style>
