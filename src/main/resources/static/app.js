// 1. ПЪЛЕН РЕЧНИК ЗА ЦЯЛОТО ПРИЛОЖЕНИЕ
const translations = {
    bg: {
        // Меню и Настройки
        nav_dashboard: "Главно табло", nav_students: "Студенти", nav_courses: "Специалности", nav_prof: "Преподаватели",
        theme: "Тема", theme_light: "Светла тема", theme_dark: "Тъмна тема", lang_title: "Език",
        
        // Главно табло
        chart_title: "Разпределение на студентите", recent_regs: "Последни регистрации",
        loading: "Зареждане...", no_data: "Няма данни", no_students: "Няма регистрирани студенти.", chart_label: "Брой студенти",
        
        // Общи за таблиците
        th_id: "ID", th_action: "Управление",
        
        // Студенти
        th_name: "Име", th_fac: "Фак. Номер", th_email: "Имейл", th_courses: "Записани курсове",
        btn_new_student: "Нов Студент", search_student: "Търси по име, фак. номер...",
        
        // Преподаватели
        th_title: "Титла", th_prof_name: "Име и Фамилия", 
        btn_new_prof: "Нов Преподавател", search_prof: "Търси преподавател...",
        
        // Курсове / Специалности
        th_course_name: "Име на курса", th_credits: "Кредити", th_professor: "Преподавател",
        btn_new_course: "Нов Курс", search_course: "Търси курс по име...",
        btn_assign_prof: "Преподавател" // За бутона "+ Преподавател"
    },
    en: {
        nav_dashboard: "Dashboard", nav_students: "Students", nav_courses: "Courses", nav_prof: "Professors",
        theme: "Theme", theme_light: "Light Mode", theme_dark: "Dark Mode", lang_title: "Language",
        
        chart_title: "Student Distribution", recent_regs: "Recent Registrations",
        loading: "Loading...", no_data: "No data available", no_students: "No students registered.", chart_label: "Number of students",
        
        th_id: "ID", th_action: "Actions",
        
        th_name: "Name", th_fac: "Faculty No.", th_email: "Email", th_courses: "Enrolled Courses",
        btn_new_student: "New Student", search_student: "Search by name, ID...",
        
        th_title: "Title", th_prof_name: "Full Name", 
        btn_new_prof: "New Professor", search_prof: "Search professor...",
        
        th_course_name: "Course Name", th_credits: "Credits", th_professor: "Professor",
        btn_new_course: "New Course", search_course: "Search course by name...",
        btn_assign_prof: "Professor"
    },
    fr: {
        nav_dashboard: "Tableau de bord", nav_students: "Étudiants", nav_courses: "Cours", nav_prof: "Professeurs",
        theme: "Thème", theme_light: "Mode Clair", theme_dark: "Mode Sombre", lang_title: "Langue",
        
        chart_title: "Répartition des étudiants", recent_regs: "Inscriptions récentes",
        loading: "Chargement...", no_data: "Aucune donnée", no_students: "Aucun étudiant inscrit.", chart_label: "Nombre d'étudiants",
        
        th_id: "ID", th_action: "Actions",
        
        th_name: "Nom", th_fac: "Numéro d'étud.", th_email: "E-mail", th_courses: "Cours inscrits",
        btn_new_student: "Nouvel Étudiant", search_student: "Rechercher un étudiant...",
        
        th_title: "Titre", th_prof_name: "Nom et Prénom", 
        btn_new_prof: "Nouveau Professeur", search_prof: "Rechercher un professeur...",
        
        th_course_name: "Nom du cours", th_credits: "Crédits", th_professor: "Professeur",
        btn_new_course: "Nouveau Cours", search_course: "Rechercher un cours...",
        btn_assign_prof: "Professeur"
    },
    de: {
        nav_dashboard: "Dashboard", nav_students: "Studenten", nav_courses: "Kurse", nav_prof: "Professoren",
        theme: "Thema", theme_light: "Heller Modus", theme_dark: "Dunkler Modus", lang_title: "Sprache",
        
        chart_title: "Studentenverteilung", recent_regs: "Letzte Anmeldungen",
        loading: "Wird geladen...", no_data: "Keine Daten vorhanden", no_students: "Keine Studenten registriert.", chart_label: "Anzahl der Studenten",
        
        th_id: "ID", th_action: "Aktionen",
        
        th_name: "Name", th_fac: "Matrikelnummer", th_email: "E-Mail", th_courses: "Eingeschriebene Kurse",
        btn_new_student: "Neuer Student", search_student: "Suchen nach Name, ID...",
        
        th_title: "Titel", th_prof_name: "Vor- und Nachname", 
        btn_new_prof: "Neuer Professor", search_prof: "Professor suchen...",
        
        th_course_name: "Kursname", th_credits: "Credits", th_professor: "Professor",
        btn_new_course: "Neuer Kurs", search_course: "Kurs suchen...",
        btn_assign_prof: "Professor"
    },
    it: {
        nav_dashboard: "Bacheca", nav_students: "Studenti", nav_courses: "Corsi", nav_prof: "Professori",
        theme: "Tema", theme_light: "Tema Chiaro", theme_dark: "Tema Scuro", lang_title: "Lingua",
        
        chart_title: "Distribuzione Studenti", recent_regs: "Ultime Registrazioni",
        loading: "Caricamento...", no_data: "Nessun dato", no_students: "Nessuno studente registrato.", chart_label: "Numero di studenti",
        
        th_id: "ID", th_action: "Azioni",
        
        th_name: "Nome", th_fac: "Matricola", th_email: "Email", th_courses: "Corsi iscritti",
        btn_new_student: "Nuovo Studente", search_student: "Cerca studente...",
        
        th_title: "Titolo", th_prof_name: "Nome e Cognome", 
        btn_new_prof: "Nuovo Professore", search_prof: "Cerca professore...",
        
        th_course_name: "Nome del corso", th_credits: "Crediti", th_professor: "Professore",
        btn_new_course: "Nuovo Corso", search_course: "Cerca corso...",
        btn_assign_prof: "Professore"
    },
    ru: {
        nav_dashboard: "Главная панель", nav_students: "Студенты", nav_courses: "Курсы", nav_prof: "Преподаватели",
        theme: "Тема", theme_light: "Светлая тема", theme_dark: "Темная тема", lang_title: "Язык",
        
        chart_title: "Распределение студентов", recent_regs: "Последние регистрации",
        loading: "Загрузка...", no_data: "Нет данных", no_students: "Нет зарегистрированных студентов.", chart_label: "Количество студентов",
        
        th_id: "ID", th_action: "Управление",
        
        th_name: "Имя", th_fac: "Номер зачетки", th_email: "Email", th_courses: "Записанные курсы",
        btn_new_student: "Новый Студент", search_student: "Поиск студента...",
        
        th_title: "Должность", th_prof_name: "Имя и Фамилия", 
        btn_new_prof: "Новый Преподаватель", search_prof: "Поиск преподавателя...",
        
        th_course_name: "Название курса", th_credits: "Кредиты", th_professor: "Преподаватель",
        btn_new_course: "Новый Курс", search_course: "Поиск курса...",
        btn_assign_prof: "Преподаватель"
    }
};

// 2. ИНЖЕКТИРАНЕ НА СВЕТЛАТА ТЕМА
const lightThemeCss = `
    [data-bs-theme="light"] body { background-color: #f4f6f9 !important; color: #212529 !important; }
    [data-bs-theme="light"] .sidebar, [data-bs-theme="light"] .table-custom, [data-bs-theme="light"] .chart-container, [data-bs-theme="light"] .modal-content { background-color: #ffffff !important; border: 1px solid #dee2e6 !important; box-shadow: 0 4px 6px rgba(0,0,0,0.05) !important; }
    [data-bs-theme="light"] .text-white, [data-bs-theme="light"] .text-light, [data-bs-theme="light"] .text-muted { color: #495057 !important; }
    [data-bs-theme="light"] .table-dark { --bs-table-bg: transparent; --bs-table-color: #212529; }
    [data-bs-theme="light"] .form-control, [data-bs-theme="light"] .form-select { background-color: #fff !important; color: #212529 !important; border-color: #ced4da !important; }
    [data-bs-theme="light"] .border-secondary { border-color: #dee2e6 !important; }
`;
const styleSheet = document.createElement("style");
styleSheet.innerText = lightThemeCss;
document.head.appendChild(styleSheet);

// 3. ПОМОЩНА ФУНКЦИЯ ЗА ПРЕВОД НА ТЕКСТОВЕ В JAVASCRIPT
window.t = function(key) {
    const lang = localStorage.getItem('campus_lang') || 'bg';
    return (translations[lang] && translations[lang][key]) ? translations[lang][key] : key;
};

// 4. ЛОГИКА ЗА СМЯНА НА ЕЗИКА (ПРЕВЕЖДА HTML-А)
window.setLanguage = function(lang) {
    localStorage.setItem('campus_lang', lang);
    document.documentElement.lang = lang;
    
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (translations[lang] && translations[lang][key]) {
            if (el.tagName === 'INPUT' && el.hasAttribute('placeholder')) {
                el.placeholder = translations[lang][key];
            } else {
                el.textContent = translations[lang][key];
            }
        }
    });

    // Рестартираме само Главното табло, за да се преначертае Chart.js графиката на новия език
    if(window.location.pathname.endsWith('index.html') || window.location.pathname === '/') {
        location.reload(); 
    } else {
        // Ако сме на друга страница (Студенти/Курсове), презареждаме таблицата, за да се преведат бутоните вътре
        if(typeof window.loadAllStudents === 'function') window.loadAllStudents();
        if(typeof window.loadAllProfessors === 'function') window.loadAllProfessors();
        if(typeof window.loadAllCourses === 'function') window.loadAllCourses();
    }
}

// 5. ЛОГИКА ЗА СМЯНА НА ТЕМАТА
window.setTheme = function(theme) {
    localStorage.setItem('campus_theme', theme);
    document.documentElement.setAttribute('data-bs-theme', theme);
}

// 6. ПЪРВОНАЧАЛНО ЗАРЕЖДАНЕ
document.addEventListener("DOMContentLoaded", function() {
    const savedTheme = localStorage.getItem('campus_theme') || 'dark';
    const savedLang = localStorage.getItem('campus_lang') || 'bg';
    
    setTheme(savedTheme);
    
    document.documentElement.lang = savedLang;
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (translations[savedLang] && translations[savedLang][key]) {
            if (el.tagName === 'INPUT' && el.hasAttribute('placeholder')) {
                el.placeholder = translations[savedLang][key];
            } else {
                el.textContent = translations[savedLang][key];
            }
        }
    });
});