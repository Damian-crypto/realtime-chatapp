const test = (metaData) => {
    fetch(`${metaData.baseURL}/home`)
        .then((res) => {
            if (!res.ok) {
                throw new Error(`Response is not ok (😶) => ${res.status}`);
            }

            console.log('Response received (👌) => test')

            return res.text();
        })
        .then((data) => console.log(data))
        .catch((err) => console.error(`Error(🫤) test => ${err}`));
};

const getToken = (metaData) => {
    fetch(`${metaData.baseURL}/authenticate`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: 'admin',
            password: 'admin'
        })
    })
        .then((res) => {
            if (!res.ok) {
                throw new Error("Error when authenticating client!")
            }

            console.log('Response received (👌) => authentication')

            return res.json();
        })
        .then((tokenData) => {
            // console.log(tokenData.jwt);
            localStorage.setItem("jwt", tokenData.jwt);
            metaData.myUserId = tokenData.userId;
        })
        .catch((err) => console.error(`Error(🫤) auth => ${err}`));
};

const getData = (metaData, callback) => {
    fetch(`${metaData.baseURL}/get-messages/${metaData.myUserId}`, {
        headers: {
            'Accept': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
            'Content-Type': 'application/json'
        },
    })
        .then((res) => {
            if (!res.ok) {
                throw new Error("Error when authenticating client!")
            }

            console.log('Response received (👌) => data')

            return res.json();
        })
        .then(callback)
        .catch((err) => console.error(`Error(🫤) data => ${err}`));
};

export { test, getData, getToken };
