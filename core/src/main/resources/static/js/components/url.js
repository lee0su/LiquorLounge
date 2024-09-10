async function fetchAndStoreUsername() {
    const response = await fetch('/api/session/username');
    if (response.ok) {

        const data = await response.json();
        const username = data.username;
        const currentUrl = window.location.href;
        const newUrl = new URL(currentUrl);

        if (username) {
            newUrl.searchParams.set('username', username);
        } else {
            newUrl.searchParams.set('username', '');
        }

        window.history.replaceState({}, '', newUrl);
    } else {
        console.error('세션 데이터 가져오기 실패:', response.status);
    }
}

fetchAndStoreUsername().then(data => console.log('URL'));