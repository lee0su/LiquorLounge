async function signOut() {
    try {
        const response = await fetch('/api/users/logout', {
            method: 'POST',
        });

        if (response.ok) {
            alert('로그아웃 되었습니다.');
            window.location.href = '/guest/main';
        } else {
            alert('로그아웃 중 오류가 발생했습니다.');
        }
    } catch (error) {
        console.error('Sign out Error1:', error);
        alert('로그아웃 중 오류가 발생했습니다. 다시 시도해주세요.')
    }
}