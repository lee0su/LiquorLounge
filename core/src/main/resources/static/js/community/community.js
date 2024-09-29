function searchPosts() {
    const criteria = document.getElementById("searchCriteria").value;
    const value = document.getElementById("searchInput").value;

    fetch(`/posts/search?criteria=${criteria}&value=${value}`)
        .then(response => response.json())
        .then(data => {
            const postTableBody = document.getElementById("postTableBody");
            postTableBody.innerHTML = "";
            data.forEach(post => {
                const row = `<tr>
                    <td>${post.id}</td>
                    <td>${post.title}</td>
                    <td>${post.author}</td>
                    <td>${post.readCount}</td>
                    <td>${new Date(post.writeDate).toLocaleDateString()}</td>
                    <td>${post.likeCount}</td>
                    <td><!-- 댓글 수 --></td>
                </tr>`;
                postTableBody.innerHTML += row;
            });
        });
}

function showLoginAlert() {
    alert("로그인 후 게시글을 작성할 수 있습니다.");
}
