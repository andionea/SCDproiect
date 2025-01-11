namespace WinFormsApp1
{
    public partial class Form1 : Form
    {
        PackageService packageService;
        List<Package> packageList;
        public Form1()
        {
            InitializeComponent();
            packageService = new PackageService();
            packageService.createConnection();

        }

        private void button1_Click(object sender, EventArgs e)
        {
            var couriersList = packageService.GetBusyCouriers();

            comboBox1.DataSource= couriersList;
            comboBox1.DisplayMember= "name";
            comboBox1.ValueMember = "id";
        }

        private void button2_Click(object sender, EventArgs e)
        {
            var couriersWithoutPending = packageService.GetCouriersWithoutPendingPackages();
            if (couriersWithoutPending.Count == 0)
            {
                MessageBox.Show("No couriers without pending packages.");
                return;
            }
            comboBox2.DataSource = couriersWithoutPending;
            comboBox2.DisplayMember = "Name";
            comboBox2.ValueMember = "Id";
        }

        private void button3_Click(object sender, EventArgs e)
        {
            var managerPackageCounts = packageService.GetManagersAndPackageCount();
            listBox1.Items.Clear();
            foreach (var (manager, count) in managerPackageCounts)
            {
                listBox1.Items.Add($"{manager.name}: {count} packages");
            }
        }
    }
}