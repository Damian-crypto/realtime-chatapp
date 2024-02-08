<script setup>
import { ref, watch, reactive } from 'vue';

import { Data } from './components/store';
import { test, getData, getToken } from './utils/utils';
import MessageContainer from './components/MessageContainer.vue';
import MessageNavigator from './components/MessageNavigator.vue';

// data -> fetch data for users [1, 2, 3, 10]
// var data = {
//     users: {
//         1: {
//             userName: 'Damian Chamel',
//             lastOnline: '2024-02-06T06:06:01.874+00:00'
//         },
//         2: {
//             userName: 'John Doe',
//             lastOnline: '2024-02-06T06:07:01.874+00:00'
//         },
//         3: {
//             userName: 'Alen Max',
//             lastOnline: '2024-01-16T01:06:01.874+00:00'
//         },
//         4: {
//             userName: 'Simon Ortiz',
//             lastOnline: '2023-08-14T06:06:01.874+00:00'
//         },
//         10: {
//             userName: 'Our Family',
//             groupMembers: [1, 2, 3]
//         }
//     },
//     messages: {
//         2: {
//             messages: [
//                 {
//                     id: 0,
//                     sender: 1,
//                     receiver: 2,
//                     content: `Hello!!!!! Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//             ornare nibh pellentesque est. Imperdiet gravida et 
//             ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//             praesent ac senectus convallis`,
//                     timestamp: '12:12 PM 27/01/2024'
//                 },
//                 {
//                     id: 1,
//                     id: 1,
//                     sender: 2,
//                     receiver: 1,
//                     content: `Hi Hi! Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//             ornare nibh pellentesque est. Imperdiet gravida et 
//             ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//             praesent ac senectus convallis`,
//                     timestamp: '1:00 PM 27/01/2024'
//                 }
//             ]
//         },
//         3: {
//             messages: [
//                 {
//                     id: 0,
//                     sender: 1,
//                     receiver: 3,
//                     content: `😂 Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//             ornare nibh pellentesque est. Imperdiet gravida et 
//             ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//             praesent ac senectus convallis`,
//                     timestamp: '12:12 PM 27/01/2024'
//                 },
//                 {
//                     id: 1,
//                     sender: 1,
//                     receiver: 3,
//                     content: `😊 Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//             ornare nibh pellentesque est. Imperdiet gravida et 
//             ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//             praesent ac senectus convallis`,
//                     timestamp: '1:00 PM 27/01/2024'
//                 }
//             ]
//         },
//         4: {
//             messages: [
//                 {
//                     id: 0,
//                     sender: 4,
//                     receiver: 1,
//                     content: `<> Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//             ornare nibh pellentesque est. Imperdiet gravida et 
//             ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//             praesent ac senectus convallis`,
//                     timestamp: '12:12 PM 27/01/2024'
//                 }
//             ]
//         },
//         // 10: {
//         //     userData: {
//         //         userID: 10
//         //     },
//         //     messages: [
//         //         {
//         //             id: 0,
//         //             sender: 3,
//         //             content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//         //     ornare nibh pellentesque est. Imperdiet gravida et 
//         //     ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//         //     praesent ac senectus convallis`,
//         //             timestamp: '12:12 PM 27/01/2024'
//         //         },
//         //         {
//         //             id: 1,
//         //             sender: 2,
//         //             content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//         //     ornare nibh pellentesque est. Imperdiet gravida et 
//         //     ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//         //     praesent ac senectus convallis`,
//         //             timestamp: '1:00 PM 27/01/2024'
//         //         },
//         //         {
//         //             id: 2,
//         //             sender: 3,
//         //             content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//         //     ornare nibh pellentesque est. Imperdiet gravida et 
//         //     ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//         //     praesent ac senectus convallis`,
//         //             timestamp: '1:00 PM 27/01/2024'
//         //         },
//         //         {
//         //             id: 3,
//         //             sender: 0,
//         //             content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//         //     ornare nibh pellentesque est. Imperdiet gravida et 
//         //     ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//         //     praesent ac senectus convallis`,
//         //             timestamp: '1:00 PM 27/01/2024'
//         //         },
//         //         {
//         //             id: 4,
//         //             sender: 1,
//         //             content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//         //     ornare nibh pellentesque est. Imperdiet gravida et 
//         //     ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//         //     praesent ac senectus convallis`,
//         //             timestamp: '1:00 PM 27/01/2024'
//         //         },
//         //         {
//         //             id: 5,
//         //             sender: 0,
//         //             content: `Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus 
//         //     ornare nibh pellentesque est. Imperdiet gravida et 
//         //     ullamcorper justo risus. Placerat vitae a nulla imperdiet 
//         //     praesent ac senectus convallis`,
//         //             timestamp: '1:00 PM 27/01/2024'
//         //         }
//         //     ]
//         // }
//     }
// };

const data = Data;

const BASE_URL = 'http://10.0.0.5:8080';

const metaData = {
    baseURL: BASE_URL,
    myUserId: 1,
    activeUser: -1
};

test(metaData);
getToken(metaData);
getData(metaData, (res) => {
    Data.data = res;
});

var prevActiveUser = null;
if (localStorage.getItem("activeUser") != null) {
    prevActiveUser = parseInt(localStorage.getItem("activeUser"));
}

var activeUser = 0;
if (prevActiveUser == null) {
    if (activeUser == 0 && Object.keys(data.data).length > 0) {
        activeUser = ref(parseInt(Object.keys(data.users)[1]));
    }
} else {
    activeUser = ref(prevActiveUser);
    metaData.activeUser = activeUser.value;
}

function activeUserChangeCallback(userId) {
    // console.log(typeof(userId));
    // console.log(`User changed from ${activeUser.value} to ${userId}`);

    activeUser.value = userId;
    metaData.activeUser = activeUser.value;
    localStorage.setItem("activeUser", activeUser.value);
}
</script>

<template>
    <div v-if="Object.keys(data.data).length != 0">
        <div class="app-window">
            <div class="navigator">
                <div class="nav-items">
                    <MessageNavigator
                        :active-user="activeUser"
                        :key="activeUser"
                        @active-user-changed="activeUserChangeCallback" />
                </div>
            </div>

            <div class="container">
                <MessageContainer
                    :key="activeUser"
                    :active-user="activeUser"
                    :meta-data="metaData"
                    />
            </div>
        </div>
    </div>
    <div v-else>
        <h1 style="color: white">Client - No Data</h1>
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
