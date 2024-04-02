<script setup>
import { ref, watch, reactive } from 'vue';

import { Data } from './components/store';
import { test, getData, getToken } from './utils/utils';
import MessageContainer from './components/MessageContainer.vue';
import MessageNavigator from './components/MessageNavigator.vue';
import LoginForm from './components/LoginForm.vue';

// data -> fetch data for users [1, 2, 3, 10]
const sample = {
	"users": {
		"1": {
			"userId": 1,
			"userName": "Damian Chamel",
			"lastOnline": "2024-02-10T06:13:02.984+00:00"
		},
		"2": {
			"userId": 2,
			"userName": "Alex Max",
			"lastOnline": "2024-02-10T06:13:02.984+00:00"
		},
		"3": {
			"userId": 3,
			"userName": "Simon Ortiz",
			"lastOnline": "2024-02-10T06:13:02.984+00:00"
		},
		"4": {
			"userId": 4,
			"userName": "John Doe",
			"lastOnline": "2024-02-10T06:13:02.984+00:00"
		}
	},
	"messages": {
		"2": {
			"messages": [
				{
					"id": 1,
					"sender": 1,
					"receiver": 2,
					"content": "Hello!!!!! Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\nornare nibh pellentesque est. Imperdiet gravida et \r\nullamcorper justo risus. Placerat vitae a nulla imperdiet \r\npraesent ac senectus convallis",
					"timestamp": "2024-02-10T06:09:00.885+00:00"
				},
				{
					"id": 2,
					"sender": 2,
					"receiver": 1,
					"content": "Hi Hi! Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\nornare nibh pellentesque est. Imperdiet gravida et \r\nullamcorper justo risus. Placerat vitae a nulla imperdiet \r\npraesent ac senectus convallis",
					"timestamp": "2024-02-10T06:09:00.885+00:00"
				}
			]
		},
		"3": {
			"messages": [
				{
					"id": 3,
					"sender": 1,
					"receiver": 3,
					"content": "😂 Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\nornare nibh pellentesque est. Imperdiet gravida et \r\nullamcorper justo risus. Placerat vitae a nulla imperdiet \r\npraesent ac senectus convallis",
					"timestamp": "2024-02-10T06:09:00.885+00:00"
				},
				{
					"id": 4,
					"sender": 1,
					"receiver": 3,
					"content": "😊 Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\nornare nibh pellentesque est. Imperdiet gravida et \r\nullamcorper justo risus. Placerat vitae a nulla imperdiet \r\npraesent ac senectus convallis",
					"timestamp": "2024-02-10T06:09:00.885+00:00"
				}
			]
		},
		"4": {
			"messages": [
				{
					"id": 5,
					"sender": 4,
					"receiver": 1,
					"content": "<> Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\nornare nibh pellentesque est. Imperdiet gravida et \r\nullamcorper justo risus. Placerat vitae a nulla imperdiet \r\npraesent ac senectus convallis",
					"timestamp": "2024-02-10T06:09:00.885+00:00"
				}
			]
		}
	}
};


Data.data = sample;
const data = Data;

const BASE_URL = 'http://10.0.0.5:8080';

const metaData = {
    baseURL: BASE_URL,
    myUserId: 1,
    activeUser: -1
};

var showLoginPage = ref(false);

test(metaData);

const dataReceived = (res) => {
    Data.data = res;
};

const dataNotReceived = (err) => {
    showLoginPage.value = true;
};

// getData(metaData, dataReceived, dataNotReceived);

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

function reAssignToken() {
    getToken(metaData);
    location.reload("/");
}
</script>

<template>
    <template v-if="!showLoginPage">
        <div v-if="Object.keys(data.data).length != 0">
            <div class="app-window">
                <div class="navigator">
                    <div class="nav-items">
                        <MessageNavigator
                            :active-user="activeUser"
                            :meta-data="metaData"
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
    <template v-else>
        <LoginForm @re-assign-token="reAssignToken" />
    </template>
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
