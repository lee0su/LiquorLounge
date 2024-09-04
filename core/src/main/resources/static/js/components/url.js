async function fetchAndStoreUsername() {
    const response = await fetch('/api/session/username');
    if (response.ok) {

        const data = await response.json();
        const username = data.username;

        if (username) {
            const currentUrl = window.location.href;
            const newUrl = new URL(currentUrl);
            newUrl.searchParams.set('username', username);

            window.history.replaceState({}, '', newUrl);
        }
    } else {
        console.error('세션 데이터 가져오기 실패:', response.status);
    }
}

fetchAndStoreUsername().then(data => console.log('URL'));