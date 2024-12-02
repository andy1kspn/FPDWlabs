// script.js
document.addEventListener('DOMContentLoaded', () => {
    const forms = document.querySelectorAll('form');

    forms.forEach(form => {
        form.addEventListener('submit', async (e) => {
            const submitButton = form.querySelector('button[type="submit"]');
            submitButton.disabled = true;
            submitButton.style.opacity = '0.7';

            setTimeout(() => {
                submitButton.disabled = false;
                submitButton.style.opacity = '1';
            }, 2000);
        });
    });

    // Add press effect to buttons
    document.querySelectorAll('.button').forEach(button => {
        button.addEventListener('mousedown', () => {
            button.style.boxShadow = 'inset 3px 3px 5px var(--shadow-dark), inset -3px -3px 5px var(--shadow-light)';
        });

        button.addEventListener('mouseup', () => {
            button.style.boxShadow = '5px 5px 10px var(--shadow-dark), -5px -5px 10px var(--shadow-light)';
        });
    });
});