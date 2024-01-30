<script setup>
import { ref, watch } from 'vue';

import MessageContainer from './components/MessageContainer.vue';
import MessageNavigator from './components/MessageNavigator.vue';

var data = {
    1: {
        userData: {
            userName: 'John Doe',
            lastOnline: ['30/01/2024', '1:00 PM'],
        },
        messages: [
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
                sender: 1,
                content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
        ornare nibh pellentesque est. Imperdiet gravida et 
        ullamcorper justo risus. Placerat vitae a nulla imperdiet 
        praesent ac senectus convallis`,
                timestamp: '1:00 PM 27/01/2024'
            }
        ]
    },
    2: {
        userData: {
            userName: 'Alen Max',
            lastOnline: ['01/04/2019', '3:00 PM']
        },
        messages: []
    },
    3: {
        userData: {
            userName: 'Simon Ortiz',
            lastOnline: ['30/01/2024', '10:00 AM']
        },
        messages: [
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
                id: 0,
                sender: 1,
                content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
        ornare nibh pellentesque est. Imperdiet gravida et 
        ullamcorper justo risus. Placerat vitae a nulla imperdiet 
        praesent ac senectus convallis`,
                timestamp: '1:00 PM 27/01/2024'
            },
            {
                id: 0,
                sender: 1,
                content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
        ornare nibh pellentesque est. Imperdiet gravida et 
        ullamcorper justo risus. Placerat vitae a nulla imperdiet 
        praesent ac senectus convallis`,
                timestamp: '1:00 PM 27/01/2024'
            },
            {
                id: 1,
                sender: 1,
                content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
        ornare nibh pellentesque est. Imperdiet gravida et 
        ullamcorper justo risus. Placerat vitae a nulla imperdiet 
        praesent ac senectus convallis`,
                timestamp: '1:00 PM 27/01/2024'
            },
            {
                id: 1,
                sender: 1,
                content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
        ornare nibh pellentesque est. Imperdiet gravida et 
        ullamcorper justo risus. Placerat vitae a nulla imperdiet 
        praesent ac senectus convallis`,
                timestamp: '1:00 PM 27/01/2024'
            },
            {
                id: 1,
                sender: 1,
                content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
        ornare nibh pellentesque est. Imperdiet gravida et 
        ullamcorper justo risus. Placerat vitae a nulla imperdiet 
        praesent ac senectus convallis`,
                timestamp: '1:00 PM 27/01/2024'
            },
        ]
    }
};

const activeUser = ref(Object.keys(data)[0]);

function activeUserChangeCallback(userId) {
    activeUser.value = userId;
}
</script>

<template>
    <div class="app-window">
        <div class="navigator">
            <div class="nav-items">
                <MessageNavigator
                :data="data"
                :active-user="activeUser"
                :key="activeUser"
                @active-user-changed="activeUserChangeCallback"/>
            </div>
        </div>

        <div class="container">
            <MessageContainer :data="data[activeUser]" :key="activeUser" />
        </div>
    </div>
</template>

<style scoped>
.app-window {
    display: flex;
    flex-direction: row;
}

.navigator {
    /* border: 1px solid red; */
    width: 350px;
}

.nav-items {
    position: fixed;
}

.container {
    flex-grow: 0.8;
    width: 500px;
}
</style>
