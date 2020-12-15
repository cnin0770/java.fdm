new Vue({
    el: '#navNotification',
    data() {
        return {
            notify: 10,
            refresh_id: null,
            refresh_every_seconds: 5,
            knocked_out: 0,
            knocked_in: 0,
            expired: 0,
            total: 0
        }
    },
    mounted() {
        this.refresh_func();
    },
    methods: {
        load_updates: async function () {
            let temp = this;
            let result = await axios.get("/api/notifications");
            let updates = result.data;
            temp.knocked_out = updates.knockedOut;
            temp.knocked_in = updates.knockedIn;
            temp.expired = updates.expired;
            temp.total = updates.total;
        },
        refresh_func: function () {
            let temp = this;
            temp.load_updates();
            temp.$nextTick(function () {
                temp.refresh_id = window.setInterval(() => {
                    temp.load_updates();
                    console.log("stocks update every " + temp.refresh_every_seconds + " seconds.");
                }, temp.refresh_every_seconds * 1000);
            });
        },
        remove: function () {
            this.total = 0;
            axios.post("/api/notifications");
        }
    }
});