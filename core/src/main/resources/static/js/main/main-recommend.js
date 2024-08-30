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
    } else {
        recommendWhiskey.style.opacity = `0`;
        recommendWhiskey.style.transform = `translateX(-35px)`;
        recommendWhiskeyText.style.opacity = `0`;
    }

    if (scrollPosition > cocktailThreshold) {
        recommendCocktail.style.opacity = `1`;
        recommendCocktail.style.transform = `translateX(0)`;
        recommendCocktailText.style.opacity = `1`;
    } else {
        recommendCocktail.style.opacity = `0`;
        recommendCocktail.style.transform = `translateX(35px)`;
        recommendCocktailText.style.opacity = `0`;
    }
});