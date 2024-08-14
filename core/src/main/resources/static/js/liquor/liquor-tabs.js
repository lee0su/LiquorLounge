let currentPage = 1;
const itemsPerPage = 24;

// tabs
async function showTab(tabName) {
    console.log('현재 페이지: ' + currentPage);
    const tabs = document.querySelectorAll('.tab-content');
    tabs.forEach(tab => {
        tab.classList.remove('active');
    });

    const selectedTab = document.getElementById(tabName);
    if (selectedTab) {
        selectedTab.classList.add('active');
    }

    const liquorList = document.querySelector('.liquor-list');
    liquorList.innerHTML = '';

    try { //
        const response = await fetch(`/api/liquors/${tabName}`);
        const data = await response.json();

        data.sort((a, b) => a.id - b.id);

        const totalItems = Array.isArray(data) ? data.length : data.items.length;
        const totalPages = Math.ceil(totalItems / itemsPerPage);

        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        const paginatedData = data.slice(startIndex, endIndex);

        paginatedData.forEach(item => {
            const liquorItem = document.createElement('div');
            liquorItem.classList.add('liquor-item');
            liquorItem.innerHTML = `
                <img src="/images/logo.png" alt="${item.name}">
                <div class="item-info">
                    <strong>${item.name}</strong>
                    <p>Proof: ${item.proof}</p>
                </div>
                <div class="item-price">$${item.pricePer70cl}</div>
            `;
            liquorList.appendChild(liquorItem);
        });

        setupPagination(currentPage, totalPages, tabName);

    } catch(error) {
        console.error('Liquor List Up Error_1 fetching liquor data: ', error);
        liquorList.innerHTML = '<p>데이터를 불러오는 데 실패했습니다.</p>';
    }

    const tabButtons = document.querySelectorAll('.tab');
    tabButtons.forEach(button => {
        button.classList.remove('active');
    });

    const activeButton = Array.from(tabButtons).find(button => button.textContent.toLowerCase() === tabName.toLowerCase());
    if (activeButton) {
        activeButton.classList.add('active');
    }
}

function setupPagination(currentPage, totalPages, tabName) {
    const pagination = document.querySelector('.pagination');
    pagination.innerHTML = '';

    if (currentPage > 1) {
        const prevButton = document.createElement('button');
        prevButton.textContent = '<<';
        prevButton.onclick = () => {
            currentPage--;
            showTab(tabName);
        };
        pagination.appendChild(prevButton);
    }

    const pageInfo = document.createElement('span');
    pageInfo.textContent = `${currentPage}/${totalPages}`;
    pagination.appendChild(pageInfo);

    if (currentPage < totalPages) {
        const nextButton = document.createElement('button');
        nextButton.textContent = '>>';
        nextButton.onclick = () => {
            currentPage++;
            console.log(currentPage);
            showTab(tabName);
        };
        pagination.appendChild(nextButton);
    }
}