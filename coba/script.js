/**
 * DATA CONFIGURATION
 * Anda bisa menambah aplikasi, mengubah harga, dan deskripsi di sini.
 */
const appsData = [
    {
        id: "netflix",
        name: "Netflix",
        icon: "fa-solid fa-play",
        color: "#E50914",
        plans: [
            {
                name: "Sharing 1 Profile",
                durations: {
                    daily: {
                        price: "Rp 6.000",
                        description: "Cocok untuk coba-coba singkat.",
                        features: ["Ultra HD 4K", "1 User / 1 Profile", "Login 1 Device", "Less Limit", "Garansi aktivasi"],
                    },
                    weekly: {
                        price: "Rp 18.000",
                        description: "Marathon series favoritmu tanpa khawatir habis dalam sehari.",
                        features: ["Ultra HD 4K", "1 User / 1 Profile", "Login 1 Device", "Less Limit", "Full Garansi", "7 Hari"],
                    },
                },
                bestSeller: false
            },
            {
                name: "Sharing 1 Profile",
                durations: {
                    monthly: {
                        price: "Rp 40.000",
                        description: "Paling cocok untuk penggunaan rutin.",
                        features: ["Ultra HD 4K", "1 User / 1 Profile", "Login 1 Device", "Less Limit", "Full Garansi", "25 -30 Hari"],
                    },
                },
                bestSeller: true
            },
            {
                name: "Semi Private 1 Profile",
                durations: {
                    monthly: {
                        price: "Rp 45.000",
                        description: "Buat yang ingin akses lebih fleksibel dengan login di 2 device sekaligus.",
                        features: ["Ultra HD 4K", "1 User / 1 Profile", "Login 2 Device", "Less Limit", "Full Garansi", "25 -30 Hari"],
                    },
                },
                bestSeller: false
            },
        ]
    },
    {
        id: "canva",
        name: "Canva",
        icon: "fa-solid fa-palette",
        color: "#00C4CC",
        plans: [
            {
                name: "Canva Pro (Tim)",
                durations: {
                    monthly: {
                        price: "Rp 4.000",
                        description: "Paket standar untuk individu dengan akses penuh ke fitur Pro.",
                        features: ["Akses konten premium", "Tools lengkap", "Berbagai fitur AI", "Undang via link"],
                    },
                },
                bestSeller: false
            },
            {
                name: "Canva Pro Design (Tim)",
                durations: {
                    monthly: {
                        price: "Rp 5.000",
                        description: "Semua Fitur Paket Pro dengan kontrol penuh.",
                        features: ["Akses konten premium", "Tools lengkap", "Berbagai fitur AI", "Fitur Desain (upload font, brand kit, color palette)", "Undang via link"],
                    },
                },
                bestSeller: true
            },
        ]
    },
    {
        id: "bstation",
        name: "Bstation",
        icon: "fa-solid fa-video",
        color: "#000000",
        plans: [
            {
                name: "Sharing 6 Members",
                durations: {
                    monthly: {
                        price: "Rp 8.000",
                        description: "Pilihan singkat untuk kebutuhan edit cepat.",
                        features: ["No Iklan", "Akses konten premium", "Login 1 Device", "Limit 3 Online Device", "Garansi Full"],
                    },
                },
                bestSeller: true
            },
        ]
    },
    // TAMBAH APLIKASI BARU DI SINI:
    // { id: "disney", name: "Disney+", ... }
];

let currentApp = appsData[0].id;

const durationLabels = {
    daily: {
        label: 'Harian',
        unit: 'hari'
    },
    weekly: {
        label: 'Mingguan',
        unit: 'minggu'
    },
    monthly: {
        label: 'Bulanan',
        unit: 'bulan'
    }
};

function renderTabs() {
    const tabsContainer = document.getElementById('app-tabs');
    tabsContainer.innerHTML = appsData.map(app => `
        <button onclick="setApp('${app.id}')" 
                class="whitespace-nowrap px-2 py-3 font-semibold transition-all ${currentApp === app.id ? 'tab-active' : 'text-gray-400 hover:text-gray-600'}">
            <i class="${app.icon} mr-2"></i> ${app.name}
        </button>
    `).join('');
}

function setApp(appId) {
    currentApp = appId;
    renderTabs();
    renderCards();
}

function renderCards() {
    const container = document.getElementById('pricing-container');
    const selectedAppData = appsData.find(app => app.id === currentApp);

    const planCards = selectedAppData.plans.flatMap(plan => {
        return Object.entries(plan.durations).map(([durationKey, durationData]) => `
            <div class="relative bg-white rounded-2xl border ${plan.bestSeller ? 'border-indigo-500 ring-2 ring-indigo-100' : 'border-gray-200'} p-8 card-shadow flex flex-col">
                ${plan.bestSeller ? '<span class="absolute -top-4 left-1/2 -translate-x-1/2 bg-indigo-600 text-white text-xs font-bold px-4 py-1 rounded-full uppercase tracking-wider">Paling Laris</span>' : ''}

                <div class="mb-6">
                    <div class="mb-3 inline-flex items-center rounded-full bg-gray-100 px-3 py-1 text-xs font-semibold uppercase tracking-wider text-gray-500">
                        ${durationLabels[durationKey]?.label || durationKey}
                    </div>
                    <h3 class="text-xl font-bold text-gray-900">${plan.name}</h3>
                    <div class="mt-4 flex items-baseline">
                        <span class="text-4xl font-extrabold tracking-tight">${durationData.price}</span>
                        <span class="ml-1 text-gray-500 text-sm">/${durationLabels[durationKey]?.unit || durationKey}</span>
                    </div>
                    ${durationData.description ? `<p class="mt-3 text-sm text-gray-500">${durationData.description}</p>` : ''}
                </div>

                <ul class="space-y-4 mb-8 flex-grow">
                    ${(durationData.features || []).map(feat => `
                        <li class="flex items-start">
                            <i class="fa-solid fa-circle-check text-green-500 mt-1 mr-3"></i>
                            <span class="text-gray-600 text-sm">${feat}</span>
                        </li>
                    `).join('')}
                </ul>

                <button onclick="orderNow('${selectedAppData.name}', '${plan.name}', '${durationData.price}', '${durationLabels[durationKey]?.label || durationKey}')" 
                        class="w-full py-3 px-6 rounded-xl font-bold transition-all ${plan.bestSeller ? 'bg-indigo-600 text-white hover:bg-indigo-700' : 'bg-gray-100 text-indigo-600 hover:bg-gray-200'}">
                    Beli Sekarang
                </button>
            </div>
        `);
    });

    container.innerHTML = planCards.join('');
}

function orderNow(app, plan, price, durationLabel) {
    const message = `Halo Admin, saya ingin order:\n\nAplikasi: ${app}\nPaket: ${plan}\nDurasi: ${durationLabel}\nHarga: ${price}\n\nMohon dibantu prosesnya.`;
    const waUrl = `https://wa.me/6287862481038?text=${encodeURIComponent(message)}`;
    window.open(waUrl, '_blank');
}

// Initialize
renderTabs();
renderCards();
