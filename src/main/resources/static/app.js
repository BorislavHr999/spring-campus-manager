// 1. РЕЧНИК С ПРЕВОДИ
const translations = {
    bg: {
        nav_dashboard: "Главно табло", nav_students: "Студенти", nav_courses: "Специалности", nav_prof: "Преподаватели",
        theme_light: "Светла тема", theme_dark: "Тъмна тема", lang_title: "Език"
    },
    en: {
        nav_dashboard: "Dashboard", nav_students: "Students", nav_courses: "Courses", nav_prof: "Professors",
        theme_light: "Light Mode", theme_dark: "Dark Mode", lang_title: "Language"
    },
    fr: {
        nav_dashboard: "Tableau de bord", nav_students: "Étudiants", nav_courses: "Cours", nav_prof: "Professeurs",
        theme_light: "Mode Clair", theme_dark: "Mode Sombre", lang_title: "Langue"
    },
    de: {
        nav_dashboard: "Dashboard", nav_students: "Studenten", nav_courses: "Kurse", nav_prof: "Professoren",
        theme_light: "Heller Modus", theme_dark: "Dunkler Modus", lang_title: "Sprache"
    },
    it: {
        nav_dashboard: "Bacheca", nav_students: "Studenti", nav_courses: "Corsi", nav_prof: "Professori",
        theme_light: "Tema Chiaro", theme_dark: "Tema Scuro", lang_title: "Lingua"
    },
    ru: {
        nav_dashboard: "Главная панель", nav_students: "Студенты", nav_courses: "Курсы", nav_prof: "Преподаватели",
        theme_light: "Светлая тема", theme_dark: "Темная тема", lang_title: "Язык"
    }
};

// 2. ЛОГИКА ЗА ЕЗИКА
function setLanguage(lang) {
    localStorage.setItem('campus_lang', lang);
    document.documentElement.lang = lang;
    
    // Намираме всички елементи, които имат атрибут data-i18n и им сменяме текста
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (translations[lang] && translations[lang][key]) {
            el.textContent = translations[lang][key];
        }
    });
}

// 3. ЛОГИКА ЗА ТЕМАТА
function setTheme(theme) {
    localStorage.setItem('campus_theme', theme);
    document.documentElement.setAttribute('data-bs-theme', theme); // Bootstrap 5 магията
}

// 4. ИНИЦИАЛИЗАЦИЯ ПРИ ЗАРЕЖДАНЕ НА СТРАНИЦАТА
document.addEventListener("DOMContentLoaded", function() {
    // Проверяваме дали потребителят вече си е избирал тема/език, ако не - слагаме по подразбиране
    const savedTheme = localStorage.getItem('campus_theme') || 'dark';
    const savedLang = localStorage.getItem('campus_lang') || 'bg';
    
    setTheme(savedTheme);
    setLanguage(savedLang);
});