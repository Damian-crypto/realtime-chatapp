<script setup>
import { ref, watch } from 'vue';

import MessageContainer from './components/MessageContainer.vue';
import MessageNavigator from './components/MessageNavigator.vue';

var rawData = [
	{
		"messageId": 3,
		"content": "Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis",
		"receiverId": 1,
		"timestamp": "2024-02-03T08:31:37.724+00:00",
		"sender": {
			"userId": 2,
			"name": "John Doe",
			"email": "john@gmail.com",
			"mobileNo": "0123456789",
			"username": "john",
			"authority": "USER",
			"password": "1234",
			"enabled": true
		},
		"attachment": null,
		"messageType": null
	},
	{
		"messageId": 2,
		"content": "Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis",
		"receiverId": 3,
		"timestamp": "2024-02-03T08:31:37.711+00:00",
		"sender": {
			"userId": 1,
			"name": "Damian Chamel",
			"email": "bdamianchamel@gmail.com",
			"mobileNo": "342341238",
			"username": "admin",
			"authority": "ADMIN, USER",
			"password": "admin",
			"enabled": true
		},
		"attachment": null,
		"messageType": null
	},
	{
		"messageId": 1,
		"content": "Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis",
		"receiverId": 2,
		"timestamp": "2024-02-03T08:31:37.707+00:00",
		"sender": {
			"userId": 1,
			"name": "Damian Chamel",
			"email": "bdamianchamel@gmail.com",
			"mobileNo": "342341238",
			"username": "admin",
			"authority": "ADMIN, USER",
			"password": "admin",
			"enabled": true
		},
		"attachment": null,
		"messageType": null
	}
];

var tmp = {
    users: {},
    messages: {}
};

rawData.forEach((d) => {
    var sender = d.sender.userId - 1;
    var receiver = d.receiverId;
    if (!tmp.users[sender]) {
        tmp.users[sender] = {};
        tmp.users[receiver] = {};
    }
    tmp['users'][sender]['userName'] = d.sender.name;
    tmp['users'][sender]['lastOnline'] = ['1', '1'];

    if (!tmp.messages[d.messageId]) {
        tmp.messages[d.messageId] = {
            userData: {},
            messages: []
        };
    }
    tmp['messages'][d.messageId]['userData']['userId'] = d.receiverId;
    tmp['messages'][d.messageId]['messages'].push({
        id: d.messageId,
        sender: sender,
        content: d.content,
        timestamp: d.timestamp
    });
});

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

data = tmp;

console.log(data);

var activeUser = 0;
if (Object.keys(data).length > 0) {
    activeUser = ref(parseInt(Object.keys(data.users)[1]));
}

function activeUserChangeCallback(userId) {
    // console.log(typeof(userId));
    // console.log(`User changed from ${activeUser.value} to ${userId}`);

    activeUser.value = userId;
}
</script>

<template>
    <div v-if="Object.keys(data).length != 0">
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
