function validateForm() {
    const title = document.getElementById('title').value.trim();
    const content = document.getElementById('content').value.trim();

    // 제목과 내용이 공백인지 확인
    if (title === '' || content === '') {
        alert('제목과 내용을 입력해 주세요.');
        return false;
    }

    // 욕설 필터링 (예시)
    const profanities = ['시발', '병신', '지랄']; // 실제 욕설 목록으로 대체
    for (let word of profanities) {
        if (title.includes(word) || content.includes(word)) {
            alert('제목이나 내용에 욕설이 포함되어 있습니다.');
            return false;
        }
    }

    return true; // 유효성 검사 통과
}