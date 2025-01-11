using System.Text.Json.Serialization;

namespace WinFormsApp1
{
    internal class Package
    {
        public int id { get; set; }
        public string deliveryAddress { get; set; } = string.Empty;
        public string createdOn { get; set; } = string.Empty;
        public PackageStatus status { get; set; } = PackageStatus.NEW;
        public Courier courier { get; set; } = new Courier();
        public bool PayOnDelivery { get; set; }
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    internal enum PackageStatus {
        NEW,
        PENDING,
        DELIVERED
    }
}
