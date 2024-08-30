const recommendWhiskey = document.querySelector('.recommend-whiskey');
const recommendCocktail = document.querySelector('.recommend-cocktail');

const recommendWhiskeyText = document.querySelector('.recommend-whiskey-text');
const recommendCocktailText = document.querySelector('.recommend-cocktail-text');

const whiskeyThreshold = window.innerHeight * 0.15;
const cocktailThreshold = window.innerHeight * 0.25;

window.addEventListener('scroll', () => {
    const scrollPosition = window.scrollY;

    if (scrollPosition > whiskeyThreshold) {
        recommendWhiskey.style.opacity = `1`;
        recommendWhiskey.style.transform = `translateX(0)`;
        recommendWhiskeyText.style.opacity = `1`;
        recommendWhiskeyText.style.transform = `translateX(0)`;
    } else {
        recommendWhiskey.style.opacity = `0`;
        recommendWhiskey.style.transform = `translateX(-35px)`;
        recommendWhiskeyText.style.opacity = `0`;
        recommendWhiskeyText.style.transform = `translateX(35px)`;

        recommendWhiskey.style.zIndex = `20`;
        recommendCocktail.style.zIndex = `50`;
    }

    if (scrollPosition > cocktailThreshold) {
        let o = parseInt(recommendCocktailText.style.opacity);

        if (o === 0) {
            recommendCocktail.style.zIndex = `50`;
        }

        recommendCocktail.style.opacity = `1`;
        recommendCocktail.style.transform = `translateX(0)`;
        recommendCocktailText.style.opacity = `1`;
        recommendCocktailText.style.transform = `translateX(0)`;
    } else {
        recommendCocktail.style.opacity = `0`;
        recommendCocktail.style.transform = `translateX(35px)`;
        recommendCocktailText.style.opacity = `0`;
        recommendCocktailText.style.transform = `translateX(-35px)`;
    }
});

recommendWhiskey.addEventListener('mouseover', () => {
    recommendWhiskey.style.zIndex = `50`;
    recommendCocktail.style.zIndex = `20`;

    recommendWhiskey.style.transform = `scale(1.2)`;
    recommendCocktail.style.transform = `scale(1)`;
});

recommendCocktail.addEventListener('mouseover', () => {
    recommendCocktail.style.zIndex = `50`;
    recommendWhiskey.style.zIndex = `20`;

    recommendCocktail.style.transform = `scale(1.2)`;
    recommendWhiskey.style.transform = `scale(1)`;
});

recommendWhiskey.addEventListener('mouseout', () => {
    recommendWhiskey.style.transform = `scale(1)`;
});

recommendCocktail.addEventListener('mouseout', () => {
    recommendCocktail.style.transform = `scale(1)`;
});