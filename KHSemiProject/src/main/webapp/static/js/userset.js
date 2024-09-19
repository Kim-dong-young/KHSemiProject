document.addEventListener('DOMContentLoaded', () => {
    const navLinks = document.querySelectorAll('.nav-link');
    const contentSections = document.querySelectorAll('.content-section');

    // URL 해시로 초기 활성화 섹션 설정
    const initialSectionId = window.location.hash.slice(1) || 'profile-settings';
    const initialSection = document.querySelector(`#${initialSectionId}`);
    const initialLink = document.querySelector(`.nav-link[data-section="${initialSectionId}"]`);

    // 초기 상태 설정
    if (initialSection) {
        initialSection.classList.add('active');
    }
    if (initialLink) {
        initialLink.classList.add('current');
    }

    // 네비게이션 링크 클릭 이벤트 처리
    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault(); // 기본 링크 동작 방지

            const sectionId = link.getAttribute('data-section');
            const targetSection = document.querySelector(`#${sectionId}`);

            // 모든 섹션을 숨기고 네비게이션 링크의 활성화 상태를 초기화
            contentSections.forEach(section => section.classList.remove('active'));
            navLinks.forEach(nav => nav.classList.remove('current'));

            // 클릭한 섹션만 보이게 하고 클릭한 링크를 활성화 상태로 변경
            if (targetSection) {
                targetSection.classList.add('active');
            }
            link.classList.add('current');

            // URL 해시를 업데이트하여 페이지 새로 고침 시에도 상태 유지
            window.location.hash = `#${sectionId}`;
        });
    });

    // 페이지 로드 시 URL 해시에 따라 섹션 활성화
    const hash = window.location.hash;
    if (hash) {
        const sectionId = hash.slice(1);
        const link = document.querySelector(`.nav-link[data-section="${sectionId}"]`);
        if (link) {
            link.click(); // 링크 클릭 시 이벤트 발생
        }
    }
});