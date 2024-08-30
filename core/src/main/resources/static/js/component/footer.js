const updateFooterPosition = () => {
    const footer = document.querySelector('.footer');
    const container = document.querySelector('.container');
    const empty = document.querySelectorAll('.empty-div');
    const navbar = document.querySelector('.navbar');

    const content = container.offsetHeight + navbar.offsetHeight;
    console.log(window.innerHeight);
    console.log(content);

    if (content < window.innerHeight) {
        footer.style.position = 'fixed';
        footer.style.bottom = '0';
        footer.style.width = '100%';

        empty.forEach(e => {
            e.style.height = '75vh';
        })

    } else {
        footer.style.position = 'relative';
    }
}

window.addEventListener('load', updateFooterPosition);
window.addEventListener('resize', updateFooterPosition);

const observer = new MutationObserver(updateFooterPosition);
observer.observe(document.querySelector('.container'), { childList: true, subtree: true })