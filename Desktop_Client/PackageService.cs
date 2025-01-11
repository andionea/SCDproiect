using System.Net.Http.Headers;
using System.Text.Json;

namespace WinFormsApp1
{
    internal class PackageService
    {
        static HttpClient client = new HttpClient();

        public void createConnection()
        {
            // Update port # in the following line.
            client.BaseAddress = new Uri("http://localhost:2000/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public List<Package> GetPackages()
        {
            List<Package> packages = null;
            HttpResponseMessage response = client.GetAsync("package").Result;
            if (response.IsSuccessStatusCode)
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);
                packages = JsonSerializer.Deserialize<List<Package>>(resultString);
                return packages;

            }
            return null;
        }

        public List<Courier> GetBusyCouriers()
        {
            List<Package> packages = this.GetPackages();
            return packages.Select(p => p.courier).DistinctBy(c => c.name).ToList();
        }

        public List<Courier> GetCouriersWithoutPendingPackages()
        {
            List<Package> packages = this.GetPackages();
            var couriers = packages
                .Where(p => p.status != PackageStatus.PENDING)
                .Select(p => p.courier)
                .DistinctBy(c => c.id)
                .ToList();
            return couriers;
        }

        public List<(Courier Manager, int PackageCount)> GetManagersAndPackageCount()
        {
            List<Package> packages = this.GetPackages();
            var managers = packages
                .GroupBy(p => p.courier)
                .Select(group => (Manager: group.Key, PackageCount: group.Count()))
                .ToList();
            return managers;
        }
    }
}
