// 1. ПЪЛЕН РЕЧНИК ЗА ГЛАВНОТО ТАБЛО
const translations = {
    bg: {
        nav_dashboard: "Главно табло", nav_students: "Студенти", nav_courses: "Специалности", nav_prof: "Преподаватели",
        theme: "Тема", theme_light: "Светла тема", theme_dark: "Тъмна тема", lang_title: "Език",
        chart_title: "Разпределение на студентите", recent_regs: "Последни регистрации",
        loading: "Зареждане...", no_students: "Няма регистрирани студенти.", chart_label: "Брой студенти"
    },
    en: {
        nav_dashboard: "Dashboard", nav_students: "Students", nav_courses: "Courses", nav_prof: "Professors",
        theme: "Theme", theme_light: "Light Mode", theme_dark: "Dark Mode", lang_title: "Language",
        chart_title: "Student Distribution", recent_regs: "Recent Registrations",
        loading: "Loading...", no_students: "No students registered.", chart_label: "Number of students"
    },
    fr: {
        nav_dashboard: "Tableau de bord", nav_students: "Étudiants", nav_courses: "Cours", nav_prof: "Professeurs",
        theme: "Thème", theme_light: "Mode Clair", theme_dark: "Mode Sombre", lang_title: "Langue",
        chart_title: "Répartition des étudiants", recent_regs: "Inscriptions récentes",
        loading: "Chargement...", no_students: "Aucun étudiant inscrit.", chart_label: "Nombre d'étudiants"
    },
    de: {
        nav_dashboard: "Dashboard", nav_students: "Studenten", nav_courses: "Kurse", nav_prof: "Professoren",
        theme: "Thema", theme_light: "Heller Modus", theme_dark: "Dunkler Modus", lang_title: "Sprache",
        chart_title: "Studentenverteilung", recent_regs: "Letzte Anmeldungen",
        loading: "Wird geladen...", no_students: "Keine Studenten registriert.", chart_label: "Anzahl der Studenten"
    },
    it: {
        nav_dashboard: "Bacheca", nav_students: "Studenti", nav_courses: "Corsi", nav_prof: "Professori",
        theme: "Tema", theme_light: "Tema Chiaro", theme_dark: "Tema Scuro", lang_title: "Lingua",
        chart_title: "Distribuzione Studenti", recent_regs: "Ultime Registrazioni",
        loading: "Caricamento...", no_students: "Nessuno studente registrato.", chart_label: "Numero di studenti"
    },
    ru: {
        nav_dashboard: "Главная панель", nav_students: "Студенты", nav_courses: "Курсы", nav_prof: "Преподаватели",
        theme: "Тема", theme_light: "Светлая тема", theme_dark: "Темная тема", lang_title: "Язык",
        chart_title: "Распределение студентов", recent_regs: "Последние регистрации",
        loading: "Загрузка...", no_students: "Нет зарегистрированных студентов.", chart_label: "Количество студентов"
    }
};

// 2. ИНЖЕКТИРАНЕ НА СВЕТЛАТА ТЕМА
const lightThemeCss = `
    [data-bs-theme="light"] body { background-color: #f4f6f9 !important; color: #212529 !important; }
    [data-bs-theme="light"] .sidebar, [data-bs-theme="light"] .table-custom, [data-bs-theme="light"] .chart-container, [data-bs-theme="light"] .modal-content { background-color: #ffffff !important; border: 1px solid #dee2e6 !important; box-shadow: 0 4px 6px rgba(0,0,0,0.05) !important; }
    [data-bs-theme="light"] .text-white, [data-bs-theme="light"] .text-light, [data-bs-theme="light"] .text-muted { color: #495057 !important; }
    [data-bs-theme="light"] .table-dark { --bs-table-bg: transparent; --bs-table-color: #212529; }
`;
const styleSheet = document.createElement("style");
styleSheet.innerText = lightThemeCss;
document.head.appendChild(styleSheet);

// 3. ПОМОЩНА ФУНКЦИЯ ЗА ПРЕВОД В ДВИЖЕНИЕ (Използва се от JavaScript-а)
window.t = function(key) {
    const lang = localStorage.getItem('campus_lang') || 'bg';
    return (translations[lang] && translations[lang][key]) ? translations[lang][key] : key;
};

// 4. ЛОГИКА ЗА СМЯНА НА ЕЗИКА
window.setLanguage = function(lang) {
    localStorage.setItem('campus_lang', lang);
    document.documentElement.lang = lang;
    
    // Сменяме текстовете в HTML-а
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (translations[lang] && translations[lang][key]) {
            el.textContent = translations[lang][key];
        }
    });

    // Презареждаме страницата, за да може графиките (Chart.js) да се нарисуват на новия език
    if(window.location.pathname.endsWith('index.html') || window.location.pathname === '/') {
        location.reload(); 
    }
}

// 5. ЛОГИКА ЗА ТЕМАТА
window.setTheme = function(theme) {
    localStorage.setItem('campus_theme', theme);
    document.documentElement.setAttribute('data-bs-theme', theme);
}

// 6. ПЪРВОНАЧАЛНО ЗАРЕЖДАНЕ
document.addEventListener("DOMContentLoaded", function() {
    const savedTheme = localStorage.getItem('campus_theme') || 'dark';
    const savedLang = localStorage.getItem('campus_lang') || 'bg';
    
    setTheme(savedTheme);
    
    // Превеждаме само HTML елементите при първо зареждане
    document.documentElement.lang = savedLang;
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (translations[savedLang] && translations[savedLang][key]) {
            el.textContent = translations[savedLang][key];
        }
    });
});