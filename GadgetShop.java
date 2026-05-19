import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GadgetShop extends JFrame implements ActionListener {

    private ArrayList<Gadget> gadgets;

    // ── Fields ────────────────────────────────────────────────────────────────
    private JTextField tfModel, tfPrice, tfWeight, tfSize;
    private JTextField tfCredit, tfPhoneNumber, tfDuration;
    private JTextField tfMemory, tfDownloadSize;
    private JTextField tfDisplayNumber;

    // ── Output ────────────────────────────────────────────────────────────────
    private JTextArea taOutput;
    private JLabel    lblStatus;
    private JLabel    lblCount;
    private DefaultTableModel tableModel;
    private JTable    gadgetTable;

    // ── Buttons ───────────────────────────────────────────────────────────────
    private JButton btnAddMobile, btnAddMP3, btnDisplayAll;
    private JButton btnMakeCall,  btnAddCredit;
    private JButton btnDownloadMusic, btnDeleteMusic;
    private JButton btnClear;
    private JButton btnFull, btnCompact;

    // ── Nav ───────────────────────────────────────────────────────────────────
    private JButton navGeneral, navMobile, navMP3, navOutput;
    private JPanel  cardContainer;
    private CardLayout cards;

    // ── Palette ───────────────────────────────────────────────────────────────
    private static final Color SIDEBAR_BG  = new Color( 28,  36,  54);
    private static final Color SIDEBAR_HOV = new Color( 40,  52,  76);
    private static final Color SIDEBAR_ACT = new Color( 55,  72, 110);

    private static final Color PAGE_BG     = new Color(242, 244, 250);
    private static final Color CARD_BG     = Color.WHITE;
    private static final Color CARD_BORDER = new Color(225, 229, 240);
    private static final Color INPUT_BORD  = new Color(200, 210, 230);
    private static final Color INPUT_FOCUS = new Color( 99, 132, 255);

    private static final Color TXT_DARK    = new Color( 22,  30,  50);
    private static final Color TXT_MID     = new Color( 80,  95, 130);
    private static final Color TXT_LIGHT   = new Color(155, 168, 200);
    private static final Color TXT_WHITE   = Color.WHITE;

    private static final Color ACC_INDIGO  = new Color( 99, 132, 255);
    private static final Color ACC_CORAL   = new Color(255,  95, 100);
    private static final Color ACC_TEAL    = new Color( 32, 196, 160);
    private static final Color ACC_AMBER   = new Color(255, 168,  40);
    private static final Color ACC_VIOLET  = new Color(155,  80, 230);

    private static final Color ROW_EVEN    = new Color(250, 251, 255);
    private static final Color ROW_SEL     = new Color(230, 236, 255);

    private static final Font F_TITLE  = new Font("SansSerif", Font.BOLD,  15);
    private static final Font F_HEAD   = new Font("SansSerif", Font.BOLD,  12);
    private static final Font F_BODY   = new Font("SansSerif", Font.PLAIN, 12);
    private static final Font F_SMALL  = new Font("SansSerif", Font.PLAIN, 11);
    private static final Font F_TINY   = new Font("SansSerif", Font.BOLD,  10);
    private static final Font F_MONO   = new Font("Monospaced",Font.PLAIN, 12);
    private static final Font F_NAV    = new Font("SansSerif", Font.BOLD,  12);

    private static final Dimension DIM_FULL    = new Dimension(1120, 760);
    private static final Dimension DIM_COMPACT = new Dimension(820,  600);

    // ═════════════════════════════════════════════════════════════════════════
    public GadgetShop() {
        gadgets = new ArrayList<>();
        buildUI();
    }

    private void buildUI() {
        setTitle("Gadget Shop — CS4001");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DIM_FULL);
        setMinimumSize(DIM_COMPACT);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(PAGE_BG);
        setContentPane(root);

        root.add(buildSidebar(),  BorderLayout.WEST);
        root.add(buildBody(),     BorderLayout.CENTER);
    }

    // ─── SIDEBAR ──────────────────────────────────────────────────────────────
    private JPanel buildSidebar() {
        JPanel side = new JPanel(new BorderLayout(0, 0));
        side.setPreferredSize(new Dimension(205, 0));
        side.setBackground(SIDEBAR_BG);

        // Logo
        JPanel logo = new JPanel(new BorderLayout(0, 4));
        logo.setBackground(new Color(18, 24, 40));
        logo.setBorder(new EmptyBorder(20, 18, 20, 18));
        JLabel icon = new JLabel("◈  Gadget Shop");
        icon.setFont(new Font("SansSerif", Font.BOLD, 15));
        icon.setForeground(TXT_WHITE);
        JLabel sub = new JLabel("  CS4001 Management");
        sub.setFont(F_SMALL);
        sub.setForeground(new Color(100, 120, 170));
        logo.add(icon, BorderLayout.CENTER);
        logo.add(sub,  BorderLayout.SOUTH);
        side.add(logo, BorderLayout.NORTH);

        // Nav
        JPanel nav = new JPanel();
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));
        nav.setBackground(SIDEBAR_BG);
        nav.setBorder(new EmptyBorder(12, 0, 0, 0));

        navLabel(nav, "FORMS");
        navGeneral = navBtn("  ⚙   General Details", "general");
        navMobile  = navBtn("  📱  Mobile Phone",     "mobile");
        navMP3     = navBtn("  🎵  MP3 Player",       "mp3");
        nav.add(navGeneral);
        nav.add(navMobile);
        nav.add(navMP3);
        nav.add(Box.createRigidArea(new Dimension(0, 14)));
        navLabel(nav, "SYSTEM");
        navOutput  = navBtn("  ▤   Console Output",  "output");
        nav.add(navOutput);
        side.add(nav, BorderLayout.CENTER);

        // Index target
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(new Color(18, 24, 40));
        bottom.setBorder(new EmptyBorder(12, 14, 16, 14));

        JLabel idxTitle = new JLabel("TARGET GADGET #");
        idxTitle.setFont(F_TINY);
        idxTitle.setForeground(new Color(80, 100, 150));
        idxTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        tfDisplayNumber = new JTextField();
        tfDisplayNumber.setBackground(new Color(30, 40, 62));
        tfDisplayNumber.setForeground(ACC_INDIGO);
        tfDisplayNumber.setCaretColor(ACC_INDIGO);
        tfDisplayNumber.setFont(new Font("Monospaced", Font.BOLD, 18));
        tfDisplayNumber.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 65, 100), 1),
                new EmptyBorder(6, 10, 6, 10)));
        tfDisplayNumber.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        tfDisplayNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfDisplayNumber.setHorizontalAlignment(JTextField.CENTER);

        lblCount = new JLabel("0 gadgets in shop");
        lblCount.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lblCount.setForeground(new Color(70, 88, 130));
        lblCount.setAlignmentX(Component.LEFT_ALIGNMENT);

        bottom.add(idxTitle);
        bottom.add(Box.createRigidArea(new Dimension(0, 6)));
        bottom.add(tfDisplayNumber);
        bottom.add(Box.createRigidArea(new Dimension(0, 6)));
        bottom.add(lblCount);
        side.add(bottom, BorderLayout.SOUTH);

        setNavActive(navGeneral);
        return side;
    }

    private void navLabel(JPanel p, String text) {
        JLabel l = new JLabel("  " + text);
        l.setFont(F_TINY);
        l.setForeground(new Color(70, 88, 130));
        l.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        l.setBorder(new EmptyBorder(0, 0, 2, 0));
        p.add(l);
    }

    private JButton navBtn(String text, String card) {
        JButton b = new JButton(text);
        b.setFont(F_NAV);
        b.setForeground(new Color(160, 175, 215));
        b.setBackground(SIDEBAR_BG);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(10, 16, 10, 16));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        b.setActionCommand("nav:" + card);
        b.addActionListener(this);
        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                if (!b.getBackground().equals(SIDEBAR_ACT)) b.setBackground(SIDEBAR_HOV);
            }
            @Override public void mouseExited(MouseEvent e) {
                if (!b.getBackground().equals(SIDEBAR_ACT)) b.setBackground(SIDEBAR_BG);
            }
        });
        return b;
    }

    private void setNavActive(JButton active) {
        for (JButton b : new JButton[]{navGeneral, navMobile, navMP3, navOutput}) {
            if (b == null) continue;
            boolean on = (b == active);
            b.setBackground(on ? SIDEBAR_ACT : SIDEBAR_BG);
            b.setForeground(on ? TXT_WHITE   : new Color(160, 175, 215));
        }
    }

    // ─── BODY ─────────────────────────────────────────────────────────────────
    private JPanel buildBody() {
        JPanel body = new JPanel(new BorderLayout(0, 0));
        body.setBackground(PAGE_BG);
        body.add(buildTopBar(),      BorderLayout.NORTH);
        body.add(buildMainContent(), BorderLayout.CENTER);
        body.add(buildActionBar(),   BorderLayout.SOUTH);
        return body;
    }

    // ─── TOP BAR ──────────────────────────────────────────────────────────────
    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new BorderLayout(0, 0));
        bar.setBackground(CARD_BG);
        bar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, CARD_BORDER),
                new EmptyBorder(10, 20, 10, 20)));

        JLabel page = new JLabel("Dashboard");
        page.setFont(F_TITLE);
        page.setForeground(TXT_DARK);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.setOpaque(false);

        lblStatus = new JLabel("●  Ready");
        lblStatus.setFont(F_SMALL);
        lblStatus.setForeground(ACC_TEAL);

        btnFull    = topBtn("⛶  Full",   ACC_INDIGO);
        btnCompact = topBtn("▭  Compact", TXT_MID);
        btnClear   = topBtn("⌫  Clear",   ACC_CORAL);

        right.add(lblStatus);
        right.add(Box.createHorizontalStrut(10));
        right.add(btnFull);
        right.add(btnCompact);
        right.add(btnClear);

        bar.add(page,  BorderLayout.WEST);
        bar.add(right, BorderLayout.EAST);
        return bar;
    }

    private JButton topBtn(String text, Color fg) {
        JButton b = new JButton(text);
        b.setFont(F_SMALL);
        b.setForeground(fg);
        b.setBackground(PAGE_BG);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CARD_BORDER, 1),
                new EmptyBorder(5, 12, 5, 12)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(this);
        return b;
    }

    // ─── MAIN CONTENT ─────────────────────────────────────────────────────────
    private JPanel buildMainContent() {
        JPanel main = new JPanel(new BorderLayout(12, 0));
        main.setBackground(PAGE_BG);
        main.setBorder(new EmptyBorder(14, 14, 8, 14));

        // Left: card pages
        cards         = new CardLayout();
        cardContainer = new JPanel(cards);
        cardContainer.setBackground(PAGE_BG);
        cardContainer.setPreferredSize(new Dimension(340, 0));

        cardContainer.add(buildGeneralCard(), "general");
        cardContainer.add(buildMobileCard(),  "mobile");
        cardContainer.add(buildMP3Card(),     "mp3");
        cardContainer.add(buildOutputCard(),  "output");
        cards.show(cardContainer, "general");

        main.add(cardContainer,       BorderLayout.WEST);
        main.add(buildInventoryCard(), BorderLayout.CENTER);
        return main;
    }

    // ─── FORM CARDS ───────────────────────────────────────────────────────────
    private JPanel buildGeneralCard() {
        JPanel c = makeCard("⚙  General Details", ACC_INDIGO);
        JPanel b = cardBody(c);
        addFormRow(b, "Model",        tfModel  = fancyField("e.g. iPhone 15"));
        addFormRow(b, "Price (£)",    tfPrice  = fancyField("e.g. 799.99"));
        addFormRow(b, "Weight (g)",   tfWeight = fancyField("e.g. 174"));
        addFormRow(b, "Size",         tfSize   = fancyField("e.g. 147 × 71 mm"));
        return c;
    }

    private JPanel buildMobileCard() {
        JPanel c = makeCard("📱  Mobile Phone", ACC_VIOLET);
        JPanel b = cardBody(c);
        divider(b, "ADD NEW MOBILE");
        addFormRow(b, "Initial Credit (min)", tfCredit      = fancyField("e.g. 120"));
        divider(b, "PHONE ACTIONS");
        addFormRow(b, "Phone Number",         tfPhoneNumber = fancyField("e.g. 07700900000"));
        addFormRow(b, "Call Duration (min)",  tfDuration    = fancyField("e.g. 10"));
        return c;
    }

    private JPanel buildMP3Card() {
        JPanel c = makeCard("🎵  MP3 Player", ACC_TEAL);
        JPanel b = cardBody(c);
        divider(b, "ADD NEW MP3");
        addFormRow(b, "Available Memory (MB)", tfMemory       = fancyField("e.g. 8000"));
        divider(b, "MUSIC ACTIONS");
        addFormRow(b, "Track / Album Size (MB)", tfDownloadSize = fancyField("e.g. 45"));
        return c;
    }

    private JPanel buildOutputCard() {
        JPanel outer = new JPanel(new BorderLayout(0, 0));
        outer.setBackground(PAGE_BG);

        JPanel hdr = cardHeader("▤  Console Output", ACC_CORAL);
        outer.add(hdr, BorderLayout.NORTH);

        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setFont(F_MONO);
        taOutput.setBackground(new Color(18, 24, 40));
        taOutput.setForeground(new Color(160, 230, 180));
        taOutput.setCaretColor(ACC_TEAL);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setBorder(new EmptyBorder(12, 14, 12, 14));

        JScrollPane scroll = new JScrollPane(taOutput);
        scroll.setBorder(BorderFactory.createLineBorder(CARD_BORDER, 1));
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        outer.add(scroll, BorderLayout.CENTER);
        return outer;
    }

    // ─── INVENTORY TABLE ──────────────────────────────────────────────────────
    private JPanel buildInventoryCard() {
        JPanel outer = new JPanel(new BorderLayout(0, 0));
        outer.setBackground(CARD_BG);
        outer.setBorder(BorderFactory.createLineBorder(CARD_BORDER, 1));

        JPanel hdr = new JPanel(new BorderLayout(0, 0));
        hdr.setBackground(CARD_BG);
        hdr.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, CARD_BORDER),
                new EmptyBorder(10, 16, 10, 16)));
        JLabel title = new JLabel("Gadget Inventory");
        title.setFont(F_HEAD);
        title.setForeground(TXT_DARK);
        JButton showAll = new JButton("Show All  ▾");
        showAll.setFont(F_SMALL);
        showAll.setForeground(ACC_INDIGO);
        showAll.setBackground(tint(ACC_INDIGO, 0.12f));
        showAll.setFocusPainted(false);
        showAll.setBorderPainted(false);
        showAll.setBorder(new EmptyBorder(5, 12, 5, 12));
        showAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        showAll.addActionListener(e -> displayAll());
        hdr.add(title,   BorderLayout.WEST);
        hdr.add(showAll, BorderLayout.EAST);
        outer.add(hdr, BorderLayout.NORTH);

        String[] cols = {"#", "Type", "Model", "Price (£)", "Weight", "Size", "Extra"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        gadgetTable = new JTable(tableModel) {
            @Override public Component prepareRenderer(TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                if (!isRowSelected(row))
                    c.setBackground(row % 2 == 0 ? ROW_EVEN : CARD_BG);
                return c;
            }
        };
        gadgetTable.setFont(F_BODY);
        gadgetTable.setForeground(TXT_DARK);
        gadgetTable.setSelectionBackground(ROW_SEL);
        gadgetTable.setSelectionForeground(TXT_DARK);
        gadgetTable.setRowHeight(30);
        gadgetTable.setIntercellSpacing(new Dimension(0, 0));
        gadgetTable.setShowGrid(false);
        gadgetTable.setShowHorizontalLines(true);
        gadgetTable.setGridColor(new Color(240, 243, 252));

        JTableHeader th = gadgetTable.getTableHeader();
        th.setBackground(new Color(248, 249, 255));
        th.setForeground(TXT_MID);
        th.setFont(new Font("SansSerif", Font.BOLD, 11));
        th.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CARD_BORDER));

        int[] widths = {30, 65, 130, 80, 65, 100, 110};
        for (int i = 0; i < widths.length; i++)
            gadgetTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);

        // Pill renderer for Type column
        gadgetTable.getColumnModel().getColumn(1).setCellRenderer(
            new DefaultTableCellRenderer() {
                @Override public Component getTableCellRendererComponent(
                        JTable t, Object val, boolean sel, boolean foc, int r, int c) {
                    JLabel lbl = new JLabel(val == null ? "" : val.toString(), SwingConstants.CENTER);
                    lbl.setOpaque(true);
                    lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
                    String v = val == null ? "" : val.toString();
                    if (sel) {
                        lbl.setBackground(ROW_SEL); lbl.setForeground(TXT_DARK);
                    } else if (v.equals("Mobile")) {
                        lbl.setBackground(new Color(235, 228, 255));
                        lbl.setForeground(ACC_VIOLET);
                    } else {
                        lbl.setBackground(new Color(220, 248, 240));
                        lbl.setForeground(ACC_TEAL);
                    }
                    lbl.setBorder(new EmptyBorder(4, 6, 4, 6));
                    return lbl;
                }
            });

        JScrollPane scroll = new JScrollPane(gadgetTable);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CARD_BG);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        outer.add(scroll, BorderLayout.CENTER);
        return outer;
    }

    // ─── ACTION BAR ───────────────────────────────────────────────────────────
    private JPanel buildActionBar() {
        JPanel bar = new JPanel();
        bar.setLayout(new BoxLayout(bar, BoxLayout.Y_AXIS));
        bar.setBackground(CARD_BG);
        bar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, CARD_BORDER),
                new EmptyBorder(10, 14, 10, 14)));

        JPanel r1 = actionRow();
        rowTag(r1, "ADD");
        btnAddMobile  = flatBtn("＋ Add Mobile",  ACC_VIOLET);
        btnAddMP3     = flatBtn("＋ Add MP3",     ACC_TEAL);
        btnDisplayAll = flatBtn("⊞ Display All", ACC_INDIGO);
        r1.add(btnAddMobile);  r1.add(hgap(8));
        r1.add(btnAddMP3);     r1.add(hgap(18));
        r1.add(btnDisplayAll);

        JPanel r2 = actionRow();
        rowTag(r2, "ACTION");
        btnMakeCall      = flatBtn("☏ Make Call",       ACC_AMBER);
        btnAddCredit     = flatBtn("⊕ Add Credit",      ACC_AMBER);
        btnDownloadMusic = flatBtn("↓ Download Music",  ACC_INDIGO);
        btnDeleteMusic   = flatBtn("✕ Delete Music",    ACC_CORAL);
        r2.add(btnMakeCall);      r2.add(hgap(6));
        r2.add(btnAddCredit);     r2.add(hgap(18));
        r2.add(btnDownloadMusic); r2.add(hgap(6));
        r2.add(btnDeleteMusic);

        bar.add(r1);
        bar.add(Box.createRigidArea(new Dimension(0, 6)));
        bar.add(r2);
        return bar;
    }

    private JPanel actionRow() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setBackground(CARD_BG);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        return p;
    }

    private void rowTag(JPanel row, String text) {
        JLabel l = new JLabel(text);
        l.setFont(F_TINY);
        l.setForeground(TXT_LIGHT);
        l.setMinimumSize(new Dimension(52, 24));
        l.setMaximumSize(new Dimension(52, 24));
        l.setPreferredSize(new Dimension(52, 24));
        row.add(l);
        row.add(hgap(4));
    }

    private JButton flatBtn(String text, Color accent) {
        JButton b = new JButton(text);
        b.setFont(F_HEAD);
        b.setForeground(accent);
        b.setBackground(tint(accent, 0.12f));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(7, 16, 7, 16));
        b.addActionListener(this);
        b.addMouseListener(new MouseAdapter() {
            final Color base = b.getBackground();
            @Override public void mouseEntered(MouseEvent e) { b.setBackground(tint(accent, 0.22f)); }
            @Override public void mouseExited(MouseEvent e)  { b.setBackground(base); }
        });
        return b;
    }

    private Component hgap(int w) { return Box.createRigidArea(new Dimension(w, 0)); }

    // ─── CARD HELPERS ─────────────────────────────────────────────────────────
    private JPanel makeCard(String title, Color accent) {
        JPanel outer = new JPanel(new BorderLayout(0, 0));
        outer.setBackground(PAGE_BG);
        outer.setBorder(BorderFactory.createLineBorder(CARD_BORDER, 1));

        outer.add(cardHeader(title, accent), BorderLayout.NORTH);

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(CARD_BG);
        body.setBorder(new EmptyBorder(14, 16, 16, 16));
        outer.add(body, BorderLayout.CENTER);
        outer.putClientProperty("body", body);
        return outer;
    }

    private JPanel cardHeader(String title, Color accent) {
        JPanel hdr = new JPanel(new BorderLayout(0, 0));
        hdr.setBackground(CARD_BG);
        hdr.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 4, 0, 0, accent),
                    BorderFactory.createMatteBorder(0, 0, 1, 0, CARD_BORDER)),
                new EmptyBorder(10, 14, 10, 14)));
        JLabel lbl = new JLabel(title);
        lbl.setFont(F_HEAD);
        lbl.setForeground(TXT_DARK);
        hdr.add(lbl, BorderLayout.WEST);
        return hdr;
    }

    private JPanel cardBody(JPanel card) {
        Object b = card.getClientProperty("body");
        return (b instanceof JPanel) ? (JPanel) b : new JPanel();
    }

    private void addFormRow(JPanel body, String label, JTextField field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(F_SMALL);
        lbl.setForeground(TXT_MID);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setBorder(new EmptyBorder(0, 0, 3, 0));

        JPanel wrapper = new JPanel(new BorderLayout(0, 0));
        wrapper.setOpaque(false);
        wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        wrapper.add(lbl,   BorderLayout.NORTH);
        wrapper.add(field, BorderLayout.CENTER);

        body.add(wrapper);
        body.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void divider(JPanel body, String text) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        row.setBorder(new EmptyBorder(4, 0, 8, 0));
        JLabel lbl = new JLabel(text);
        lbl.setFont(F_TINY);
        lbl.setForeground(TXT_LIGHT);
        JSeparator sep = new JSeparator();
        sep.setForeground(CARD_BORDER);
        row.add(lbl, BorderLayout.WEST);
        row.add(sep, BorderLayout.CENTER);
        body.add(row);
    }

    private JTextField fancyField(String hint) {
        JTextField f = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    g.setColor(new Color(190, 200, 220));
                    g.setFont(getFont().deriveFont(Font.ITALIC));
                    Insets ins = getInsets();
                    g.drawString(hint, ins.left, getHeight()/2 + g.getFontMetrics().getAscent()/2 - 2);
                }
            }
        };
        f.setFont(F_BODY);
        f.setForeground(TXT_DARK);
        f.setBackground(new Color(250, 251, 255));
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(INPUT_BORD, 1),
                new EmptyBorder(7, 10, 7, 10)));
        f.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                f.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(INPUT_FOCUS, 2),
                        new EmptyBorder(6, 9, 6, 9)));
                f.repaint();
            }
            @Override public void focusLost(FocusEvent e) {
                f.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(INPUT_BORD, 1),
                        new EmptyBorder(7, 10, 7, 10)));
                f.repaint();
            }
        });
        return f;
    }

    private Color tint(Color c, float a) {
        return new Color(
                (int)(c.getRed()   + (255 - c.getRed())   * (1 - a)),
                (int)(c.getGreen() + (255 - c.getGreen()) * (1 - a)),
                (int)(c.getBlue()  + (255 - c.getBlue())  * (1 - a)));
    }

    // ─── REFRESH ──────────────────────────────────────────────────────────────
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < gadgets.size(); i++) {
            Gadget g = gadgets.get(i);
            String type  = (g instanceof Mobile) ? "Mobile" : "MP3";
            String extra = (g instanceof Mobile)
                    ? ((Mobile) g).getCallingCredit() + " min"
                    : ((MP3)    g).getAvailableMemory() + " MB";
            tableModel.addRow(new Object[]{
                i, type, g.getModel(),
                String.format("%.2f", g.getPrice()),
                g.getWeight(), g.getSize(), extra
            });
        }
    }

    private void refreshCount() {
        int n = gadgets.size();
        lblCount.setText(n + " gadget" + (n == 1 ? "" : "s") + " in shop");
    }

    private void setStatus(String msg) { lblStatus.setText("●  " + msg); }

    // ─── INPUT READERS ────────────────────────────────────────────────────────
    private String readModel()       { return tfModel.getText().trim(); }
    private String readSize()        { return tfSize.getText().trim(); }
    private String readPhoneNumber() { return tfPhoneNumber.getText().trim(); }

    private double readPrice() {
        try { return Double.parseDouble(tfPrice.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Price must be a decimal number."); return 0.0; }
    }
    private int readWeight() {
        try { return Integer.parseInt(tfWeight.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Weight must be a whole number."); return 0; }
    }
    private int readCredit() {
        try {
            int v = Integer.parseInt(tfCredit.getText().trim());
            if (v < 0) { appendOutput("Error: Credit cannot be negative."); return 0; }
            return v;
        } catch (NumberFormatException e) { appendOutput("Error: Credit must be a whole number."); return 0; }
    }
    private int readMemory() {
        try {
            int v = Integer.parseInt(tfMemory.getText().trim());
            if (v < 0) { appendOutput("Error: Memory cannot be negative."); return 0; }
            return v;
        } catch (NumberFormatException e) { appendOutput("Error: Memory must be a whole number."); return 0; }
    }
    private int readDuration() {
        try { return Integer.parseInt(tfDuration.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Duration must be a whole number."); return 0; }
    }
    private int readDownloadSize() {
        try { return Integer.parseInt(tfDownloadSize.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Download size must be a whole number."); return 0; }
    }

    private int getDisplayNumber() {
        try {
            int input = Integer.parseInt(tfDisplayNumber.getText().trim());
            if (input >= 0 && input < gadgets.size()) return input;
            JOptionPane.showMessageDialog(this,
                "Display number " + input + " is out of range.\n"
                + "Please enter a value between 0 and " + (gadgets.size()-1) + ".",
                "Out of Range", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "The display number must be a whole number (integer).",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    // ─── ACTION HANDLER ───────────────────────────────────────────────────────
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd != null && cmd.startsWith("nav:")) {
            String page = cmd.substring(4);
            cards.show(cardContainer, page);
            switch (page) {
                case "general": setNavActive(navGeneral); break;
                case "mobile":  setNavActive(navMobile);  break;
                case "mp3":     setNavActive(navMP3);     break;
                case "output":  setNavActive(navOutput);  break;
            }
            return;
        }
        Object src = e.getSource();
        if      (src == btnAddMobile)     addMobile();
        else if (src == btnAddMP3)        addMP3();
        else if (src == btnDisplayAll)    displayAll();
        else if (src == btnMakeCall)      makeCall();
        else if (src == btnAddCredit)     addCredit();
        else if (src == btnDownloadMusic) downloadMusic();
        else if (src == btnDeleteMusic)   deleteMusic();
        else if (src == btnClear)         clearFields();
        else if (src == btnFull)          setFullDisplay();
        else if (src == btnCompact)       setCompactDisplay();
    }

    // ─── BUTTON HANDLERS ──────────────────────────────────────────────────────
    private void addMobile() {
        Mobile m = new Mobile(readModel(), readPrice(), readWeight(), readSize(), readCredit());
        gadgets.add(m);
        appendOutput("Mobile added at index " + (gadgets.size()-1) + ":");
        captureDisplay(m); appendSep();
        refreshCount(); refreshTable();
        setStatus("Mobile phone added — index " + (gadgets.size()-1));
        showOutput();
    }

    private void addMP3() {
        MP3 mp3 = new MP3(readModel(), readPrice(), readWeight(), readSize(), readMemory());
        gadgets.add(mp3);
        appendOutput("MP3 added at index " + (gadgets.size()-1) + ":");
        captureDisplay(mp3); appendSep();
        refreshCount(); refreshTable();
        setStatus("MP3 player added — index " + (gadgets.size()-1));
        showOutput();
    }

    private void clearFields() {
        for (JTextField f : new JTextField[]{
                tfModel, tfPrice, tfWeight, tfSize,
                tfCredit, tfPhoneNumber, tfDuration,
                tfMemory, tfDownloadSize, tfDisplayNumber})
            f.setText("");
        taOutput.setText("");
        setStatus("All fields and output cleared.");
    }

    private void displayAll() {
        if (gadgets.isEmpty()) {
            appendOutput("No gadgets in the shop yet.");
            setStatus("No gadgets to display.");
        } else {
            appendOutput("=== All Gadgets (" + gadgets.size() + " item"
                    + (gadgets.size()==1?"":"s") + ") ===");
            for (int i = 0; i < gadgets.size(); i++) {
                appendOutput("Gadget #" + i + "  ["
                        + (gadgets.get(i) instanceof Mobile ? "Mobile" : "MP3") + "]:");
                captureDisplay(gadgets.get(i)); appendSep();
            }
            setStatus("Displayed all " + gadgets.size() + " gadget(s).");
        }
        showOutput();
    }

    private void makeCall() {
        int idx = getDisplayNumber(); if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof Mobile)) { appendOutput("Error: Gadget #"+idx+" is not a Mobile phone."); return; }
        redirectAndCall(() -> ((Mobile)g).makeCall(readPhoneNumber(), readDuration()));
        appendSep(); refreshTable();
        setStatus("Make A Call executed on gadget #" + idx); showOutput();
    }

    private void addCredit() {
        int idx = getDisplayNumber(); if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof Mobile)) { appendOutput("Error: Gadget #"+idx+" is not a Mobile phone."); return; }
        redirectAndCall(() -> ((Mobile)g).addCredit(readCredit()));
        appendSep(); refreshTable();
        setStatus("Add Credit executed on gadget #" + idx); showOutput();
    }

    private void downloadMusic() {
        int idx = getDisplayNumber(); if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof MP3)) { appendOutput("Error: Gadget #"+idx+" is not an MP3 player."); return; }
        redirectAndCall(() -> ((MP3)g).downloadMusic(readDownloadSize()));
        appendSep(); refreshTable();
        setStatus("Download Music executed on gadget #" + idx); showOutput();
    }

    private void deleteMusic() {
        int idx = getDisplayNumber(); if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof MP3)) { appendOutput("Error: Gadget #"+idx+" is not an MP3 player."); return; }
        redirectAndCall(() -> ((MP3)g).deleteMusic(readDownloadSize()));
        appendSep(); refreshTable();
        setStatus("Delete Music executed on gadget #" + idx); showOutput();
    }

    private void setFullDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setStatus("Full Display mode.");
    }

    private void setCompactDisplay() {
        setExtendedState(JFrame.NORMAL);
        setSize(DIM_COMPACT);
        setLocationRelativeTo(null);
        setStatus("Compact Display mode.");
    }

    private void showOutput() {
        cards.show(cardContainer, "output");
        setNavActive(navOutput);
    }

    // ─── UTILITIES ────────────────────────────────────────────────────────────
    private void captureDisplay(Gadget gadget) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(baos));
        gadget.display();
        System.out.flush(); System.setOut(old);
        taOutput.append(baos.toString());
        if (!baos.toString().endsWith("\n")) taOutput.append("\n");
    }

    private void redirectAndCall(Runnable action) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(baos));
        action.run();
        System.out.flush(); System.setOut(old);
        taOutput.append(baos.toString());
        if (!baos.toString().endsWith("\n")) taOutput.append("\n");
    }

    private void appendOutput(String text) {
        taOutput.append(text + "\n");
        taOutput.setCaretPosition(taOutput.getDocument().getLength());
    }

    private void appendSep() {
        taOutput.append("─────────────────────────────\n");
        taOutput.setCaretPosition(taOutput.getDocument().getLength());
    }

    // ═════════════════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GadgetShop().setVisible(true));
    }
}
