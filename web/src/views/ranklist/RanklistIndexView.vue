<template>
    <ContentField>
        <table class="table  table-striped table-hover">
            <thead>
                <tr>
                    <th scope="col">玩家</th>
                    <th scope="col">天梯分</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key=user.id>
                    <td><img :src="user.photo" class="record-user-photo" alt="">
                        &nbsp;
                        <span>{{ user.username }}</span>
                    </td>
                    <td>{{ user.rating }}</td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous" @click="click_page(-2)">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number"
                    @click="click_page(page.number)">
                    <a class="page-link" href="#">{{ page.number }}</a>
                </li>

                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" @click="click_page(-1)">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </ContentField>
</template>

<script>
import ContentField from '../../components/ContentField.vue'
import { ref } from 'vue';
import $ from 'jquery'
import { useStore } from 'vuex';

export default {
    components: {
        ContentField
    },
    setup() {
        let users = ref([]);
        const store = useStore();
        let current_page = 1;
        let max_pages = 0;
        let pages = ref([]);

        const click_page = page => {
            if (page === -2) page = current_page - 1;
            else if (page === -1) page = current_page + 1;
            if (page >= 1 && page <= max_pages) {
                pull_page(page);
            }
        }

        const update_pages = () => {
            let new_pages = [];
            for (let i = current_page - 2; i <= current_page + 2; i++) {
                if (i >= 1 && i <= max_pages) {
                    new_pages.push({
                        number: i,
                        is_active: i === current_page ? "active" : "",
                    });
                }
            }
            pages.value = new_pages;
        }

        const pull_page = page => {
            current_page = page;
            $.ajax({
                url: "http://127.0.0.1:3000/ranklist/getlist/",
                data: {
                    page,
                },
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    users.value = resp.users;
                    max_pages = resp.max_pages;
                    update_pages();
                },
                error(resp) {
                    console.log(resp);
                }
            });
        }

        pull_page(current_page);


        return {
            users,
            pages,
            click_page,
        }
    }
}
</script>

<style scoped>
img.record-user-photo {
    width: 4vh;
    border-radius: 50%;
}
</style>
