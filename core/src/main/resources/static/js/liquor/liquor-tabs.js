// tabs
function showTab(tabName) {
    const tabs = document.querySelectorAll('.tab-content');
    tabs.forEach(tab => {
        tab.classList.remove('active'); // 모든 탭 숨기기
    });

    const selectedTab = document.getElementById(tabName);
    selectedTab.classList.add('active'); // 선택한 탭 보이기

    const tabButtons = document.querySelectorAll('.tab');
    tabButtons.forEach(button => {
        button.classList.remove('active'); // 모든 버튼 비활성화
    });

    const activeButton = Array.from(tabButtons).find(button => button.textContent.toLowerCase() === tabName);
    activeButton.classList.add('active'); // 선택한 버튼 활성화
}