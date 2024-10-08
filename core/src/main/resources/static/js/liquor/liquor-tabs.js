const itemsPerPage = 24;

// tabs
async function showTab(tabName, currentPage) {

    const tabButtons = document.querySelectorAll('.tab');

    tabButtons.forEach(button => {
        button.classList.remove('active');
    });

    const activeButton = Array.from(tabButtons).find(button => button.textContent.toLowerCase() === tabName);

    if (activeButton) {
        activeButton.classList.add('active');
    }

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

    try {
        const response = await fetch(`/api/liquors/${tabName}`);
        const data = await response.json();

        data.sort((a, b) => a.id - b.id);

        const totalItems = data.length;
        const totalPages = Math.ceil(totalItems / itemsPerPage);

        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        const paginatedData = data.slice(startIndex, endIndex);

        const imgUrl = '/images/temp_img.png';

        paginatedData.forEach(item => {
            const liquorItem = document.createElement('div');
            liquorItem.classList.add('liquor-item');
            if (tabName === 'whiskey') {
                liquorItem.innerHTML = `
                    <img src="${item.img}" alt="${item.name}">
                    <div class="item-info">
                        <strong class="item-name">${item.name}</strong>
                        <p class="item-abv">ABV: ${(item.proof / 2).toFixed(1)}%</p>
                        <p class="item-price">70cl $${item.pricePer70cl}<p>
                    </div>
                `;
            } else if (tabName === 'gin') {
                liquorItem.innerHTML = `
                    <img src="${item.img}" alt="${item.name}">
                    <div class="item-info">
                        <strong class="item-name">${item.name}</strong>
                        <p class="item-abv">ABV: ${item.alcohol}%</p>
                        <p class="item-price">$??</p>
                    </div>
                `;
            } else if (tabName === 'rum') {
                liquorItem.innerHTML = `
                    <img src="${imgUrl}" alt="${item.name}">
                    <div class="item-info">
                        <strong class="item-name">${item.name}</strong>
                        <p class="item-abv">ABV: ?%</p>
                        <p class="item-price">$??</p>
                    </div>
                `;
            } else if (tabName === 'wine') {
                liquorItem.innerHTML = `
                    <img src="${item.img}" alt="${item.name}">
                    <div class="item-info">
                        <strong class="item-name"${item.name}</strong>
                        <p class="item-abv">ABV: ${item.abv}%</p>
                        <p class="item-price">$${item.price}</p>
                    </div>
                `;
            } else if (tabName === 'other') {

            }

            liquorList.appendChild(liquorItem);
        });

        if (totalPages < currentPage) {
            liquorList.innerHTML = '<p>데이터를 준비중입니다.</div>';
            setupPagination(0, 0, tabName);
        } else {
            setupPagination(currentPage, totalPages, tabName);
        }

    } catch(error) {
        console.error('Liquor List Up Error_1 fetching liquor data: ', error);
        // liquorList.innerHTML = '<p>데이터를 불러오는데 실패했습니다.</p>';
        liquorList.innerHTML = '<p>데이터를 준비중입니다.</p>';
        setupPagination(0, 0, tabName);
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
            showTab(tabName, currentPage);
        };
        pagination.appendChild(prevButton);
    } else {
        const temp = document.createElement('span');
        temp.textContent = '';
        pagination.appendChild(temp);
    }

    const pageInfo = document.createElement('p');
    pageInfo.textContent = `${currentPage}/${totalPages}`;
    pagination.appendChild(pageInfo);

    if (currentPage < totalPages) {
        const nextButton = document.createElement('button');
        nextButton.textContent = '>>';
        nextButton.onclick = () => {
            currentPage++;
            showTab(tabName, currentPage);
        };
        pagination.appendChild(nextButton);
    } else {
        const temp = document.createElement('p');
        temp.textContent = '';
        pagination.appendChild(temp);
    }
}