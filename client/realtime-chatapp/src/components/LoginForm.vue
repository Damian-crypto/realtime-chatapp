<script>
import { ref } from 'vue';

export default {
    emits: ['re-assign-token'],

    setup(props, { emit }) {
        const password = ref("");
        const username = ref("");

        function reAssignToken() {
            if (password.value == "" || username.value == "") {
                alert("Password or Username field(s) cannot be empty!");
                return;
            }

            emit('re-assign-token');
        }

        return {
            reAssignToken,
            password,
            username
        };
    }
}
</script>

<template>
    <div class="container">
        <div class="login-form">
            <h1>Login</h1>
            <table>
                <tr>
                    <th><label class="login-form-label" for="username">Username</label></th>
                    <td><input class="login-form-input" v-model="username" type="text" placeholder="e.g., john" name="username" id="username" required></td>
                </tr>
                <tr>
                    <th><label class="login-form-label" for="password">Password</label></th>
                    <td><input class="login-form-input" v-model="password" type="password" placeholder="e.g., 1234" name="password" id="password" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button id="btn-login" class="login-form-button" @click="reAssignToken">Login</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</template>

<style scoped>
.container {
    /* border: 1px solid red; */

    height: 100vh;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 500px;
}

.login-form {
    border: 5px solid #3b3b3b;

    padding: 0.8em;
    display: inline-block;
    border-radius: 10px;
    background-color: #414141;
}

.login-form-input {
    font-size: 20px;
    border-radius: 20px;
    padding: 0.3em 0.8em;
    border: none;
}

.login-form-label {
    font-size: 20px;
}

.login-form-button {
    font-size: 20px;
    padding: 0.3em 0.8em;
    border-radius: 20px;
}

#btn-login {
    float: right;
}
</style>
