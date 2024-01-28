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
    top: 44px;
    left: 254px;
    padding: 1px;
    position: absolute;
}

#txt-search {
    font-size: 24px;
    border: none;
    border-radius: 50px;
    padding: 3px 40px 3px 15px;
    width: 250px;
}
</style>
