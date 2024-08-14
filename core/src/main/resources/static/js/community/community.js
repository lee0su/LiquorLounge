function updatePlaceholder() {
    const criteria = document.getElementById('searchCriteria').value;
    const searchInput = document.getElementById('searchInput');

    switch (criteria) {
        case 'title':
            searchInput.placeholder = '제목 검색';
            break;
        case 'author':
            searchInput.placeholder = '작성자 검색';
            break;
        case 'content':
            searchInput.placeholder = '내용 검색';
            break;
    }
}
