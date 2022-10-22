<template>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">name</th>
                <th scope="col">host:port</th>
                <th scope="col">ops</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>server1</td>
                <td>10.10.10.1:22</td>
                <td><a href="#">连接</a></td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>server2</td>
                <td>10.10.10.2:22</td>
                <td><a href="#">连接</a></td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>server3</td>
                <td>10.10.10.3:22</td>
                <td><a href="#">连接</a></td>
            </tr>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script>
export default {
    name: "HistoryView"
}
</script>

<style scoped>

</style>