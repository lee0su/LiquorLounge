document.addEventListener('DOMContentLoaded', () => {
    const navbar = document.querySelector('.navbar');
    const buttons = navbar.querySelectorAll('button');

    // data-target-text 속성에서 버튼 텍스트 가져오기
    const targetText = document.body.getAttribute('data-target-text');

    buttons.forEach(button => {
        if (button.textContent.trim() === targetText) {
            button.classList.add('active');
        }
    });
});