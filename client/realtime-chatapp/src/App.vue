<script setup>
import { ref, watch } from 'vue';

import MessageContainer from './components/MessageContainer.vue';
import MessageNavigator from './components/MessageNavigator.vue';

// data -> fetch data for users [1, 2, 3, 10]
var data = {
    users: {
        0: {
            userName: 'Damian',
            lastOnline: ['31/01/2024', '12:20 PM']
        },
        1: {
            userName: 'John Doe',
            lastOnline: ['30/01/2024', '1:00 PM']
        },
        2: {
            userName: 'Alen Max',
            lastOnline: ['01/04/2019', '3:00 PM']
        },
        3: {
            userName: 'Simon Ortiz',
            lastOnline: ['30/01/2024', '10:00 AM']
        },
        10: {
            userName: 'Our Family',
            groupMembers: [1, 2, 3]
        }
    },
    messages: {
        1: {
            userData: {
                userID: 1
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
                userID: 2
            },
            messages: []
        },
        3: {
            userData: {
                userID: 3
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
        },
        10: {
            userData: {
                userID: 10
            },
            messages: [
                {
                    id: 0,
                    sender: 3,
                    content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
            ornare nibh pellentesque est. Imperdiet gravida et 
            ullamcorper justo risus. Placerat vitae a nulla imperdiet 
            praesent ac senectus convallis`,
                    timestamp: '12:12 PM 27/01/2024'
                },
                {
                    id: 1,
                    sender: 2,
                    content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
            ornare nibh pellentesque est. Imperdiet gravida et 
            ullamcorper justo risus. Placerat vitae a nulla imperdiet 
            praesent ac senectus convallis`,
                    timestamp: '1:00 PM 27/01/2024'
                },
                {
                    id: 2,
                    sender: 3,
                    content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
            ornare nibh pellentesque est. Imperdiet gravida et 
            ullamcorper justo risus. Placerat vitae a nulla imperdiet 
            praesent ac senectus convallis`,
                    timestamp: '1:00 PM 27/01/2024'
                },
                {
                    id: 3,
                    sender: 0,
                    content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
            ornare nibh pellentesque est. Imperdiet gravida et 
            ullamcorper justo risus. Placerat vitae a nulla imperdiet 
            praesent ac senectus convallis`,
                    timestamp: '1:00 PM 27/01/2024'
                },
                {
                    id: 4,
                    sender: 1,
                    content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
            ornare nibh pellentesque est. Imperdiet gravida et 
            ullamcorper justo risus. Placerat vitae a nulla imperdiet 
            praesent ac senectus convallis`,
                    timestamp: '1:00 PM 27/01/2024'
                },
                {
                    id: 5,
                    sender: 0,
                    content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
            ornare nibh pellentesque est. Imperdiet gravida et 
            ullamcorper justo risus. Placerat vitae a nulla imperdiet 
            praesent ac senectus convallis`,
                    timestamp: '1:00 PM 27/01/2024'
                }
            ]
        }
    }
};

const activeUser = ref(parseInt(Object.keys(data.users)[1]));

function activeUserChangeCallback(userId) {
    // console.log(typeof(userId));
    // console.log(`User changed from ${activeUser.value} to ${userId}`);

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
            <MessageContainer :data="data" :key="activeUser" :active-user="activeUser" />
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
